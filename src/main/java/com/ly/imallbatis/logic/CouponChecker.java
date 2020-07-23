package com.ly.imallbatis.logic;

import com.ly.imallbatis.core.Enumeration.CouponType;
import com.ly.imallbatis.core.money.IMoneyDiscount;
import com.ly.imallbatis.exception.http.ForbiddenException;
import com.ly.imallbatis.exception.http.ParameterException;
import com.ly.imallbatis.model.Coupon;
import com.ly.imallbatis.model.UserCoupon;
import com.ly.imallbatis.util.CommonUtil;

import java.math.BigDecimal;
import java.util.Date;

public class CouponChecker {

    private Coupon coupon;

    private UserCoupon userCoupon;

    private IMoneyDiscount iMoneyDiscount;

    public CouponChecker(Coupon coupon, UserCoupon userCoupon, IMoneyDiscount iMoneyDiscount) {
        this.coupon = coupon;
        this.userCoupon = userCoupon;
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
     * */
    public void canBeUsed() {}

}
