package com.ly.imallbatis.logic;

import com.ly.imallbatis.bo.SkuOrderBO;
import com.ly.imallbatis.core.Enumeration.CouponType;
import com.ly.imallbatis.core.money.IMoneyDiscount;
import com.ly.imallbatis.exception.http.ForbiddenException;
import com.ly.imallbatis.exception.http.ParameterException;
import com.ly.imallbatis.model.Category;
import com.ly.imallbatis.model.Coupon;
import com.ly.imallbatis.util.CommonUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CouponChecker {

    private Coupon coupon;

    private IMoneyDiscount iMoneyDiscount;

    public CouponChecker(Coupon coupon, IMoneyDiscount iMoneyDiscount) {
        this.coupon = coupon;
        this.iMoneyDiscount = iMoneyDiscount;
    }

    /**
     * 优惠券是否过期
     * */
    public void isOk() {
        Date now = new Date();
        Boolean isInTimeLine = CommonUtil.isInTimeLine(now, coupon.getStartTime(), coupon.getEndTime());
        if (!isInTimeLine) {
            throw new ForbiddenException(40007);
        }
    }

    /**
     * 订单最终成交价格是不是正确
     * @param orderFinalTotalPrice 前端计算最终总价
     * @param serverTotalPrice 服务器计算总价
     * */
    public void finalTotalPriceIsOk(BigDecimal orderFinalTotalPrice, BigDecimal serverTotalPrice) {
        // 服务器计算最终总价
        BigDecimal serverFinalTotalPrice = null;
        switch (CouponType.toType(coupon.getType())) {
            case FULL_MINUS:
            case NO_THRESHOLD_MINUS:
                serverFinalTotalPrice = serverTotalPrice.subtract(coupon.getMinus());
                if (serverFinalTotalPrice.compareTo(new BigDecimal("0")) <= 0) {
                    throw new ForbiddenException(50008);
                }
                break;
            case FULL_OFF:
                serverFinalTotalPrice = iMoneyDiscount.discount(serverTotalPrice, coupon.getRate());
                break;

            default:
                throw new ParameterException(40009);
        }
        int compare = serverFinalTotalPrice.compareTo(orderFinalTotalPrice);
        if (compare != 0) {
            throw new ForbiddenException(50008);
        }
    }

    /**
     * 核对当前优惠券是否真的能被使用
     * @param serverTotalPrice 服务端计算订单总价
     * @param skuOrderBOList
     * */
    public void canBeUsed(List<SkuOrderBO> skuOrderBOList, BigDecimal serverTotalPrice) {
        // 当前优惠券对应分类所有商品总价格
        BigDecimal orderCategoryPrice = null;
        if (coupon.getWholeStore()) {
            orderCategoryPrice = serverTotalPrice;
        } else {
            List<Long> cidList = coupon.getCategories().stream()
                                .map(Category::getId)
                                .collect(Collectors.toList());
            orderCategoryPrice = getSumByCategoryList(skuOrderBOList, cidList);
        }
        couponCanBeUsed(orderCategoryPrice);
    }

    /**
     * 优惠券对应分类商品总价格 和 优惠券门槛相比较
     * @param orderCategoryPrice 优惠券对应分类商品总价格
     * */
    private void couponCanBeUsed(BigDecimal orderCategoryPrice) {
        switch (CouponType.toType(coupon.getType())) {
            case FULL_OFF:
            case FULL_MINUS:
                int compare = coupon.getFullMoney().compareTo(orderCategoryPrice);
                if (compare > 0) {
                    throw new ParameterException(40008);
                }
                break;
            case NO_THRESHOLD_MINUS: //  无门槛
                break;
            default:
                throw new ParameterException(40009);
        }
    }

    /**
     * 根据分类计算所属商品价格总和
     * @param cid 分类id
     * @param skuOrderBOList 源数据
     * */
    private BigDecimal getSumByCategory(List<SkuOrderBO> skuOrderBOList, Long cid) {
        return skuOrderBOList.stream()
                .filter(sku -> sku.getCategoryId().equals(cid)) // 筛选符合条件的 sku
                .map(bo -> bo.getTotalPrice()) // 每个 sku 的 totalPrice 列表
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal("0"));
    }

    /**
     * @param skuOrderBOList 数据源
     * @param cidList 分类 id 列表
     * */
    public BigDecimal getSumByCategoryList(List<SkuOrderBO> skuOrderBOList, List<Long> cidList) {
        return cidList.stream()
                .map(cid -> getSumByCategory(skuOrderBOList, cid))
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal("0"));
    }

}
