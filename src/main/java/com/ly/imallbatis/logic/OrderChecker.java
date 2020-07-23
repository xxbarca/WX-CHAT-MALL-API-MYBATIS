package com.ly.imallbatis.logic;

import com.ly.imallbatis.bo.SkuOrderBO;
import com.ly.imallbatis.dto.OrderDTO;
import com.ly.imallbatis.dto.SkuInfoDTO;
import com.ly.imallbatis.exception.http.ParameterException;
import com.ly.imallbatis.model.OrderSku;
import com.ly.imallbatis.model.Sku;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderChecker {

    private OrderDTO orderDTO;
    private List<Sku> serverSkuList;
    private CouponChecker couponChecker;
    private Integer maxSkuLimit;

    @Getter
    private List<OrderSku> orderSkuList = new ArrayList<>();

    public OrderChecker(OrderDTO orderDTO, List<Sku> serverSkuList, CouponChecker couponChecker, Integer maxSkuLimit) {
        this.orderDTO = orderDTO;
        this.serverSkuList = serverSkuList;
        this.couponChecker = couponChecker;
        this.maxSkuLimit = maxSkuLimit;
    }

    /**
     * 获取订单图片, 一组sku中第一个的图片
     * */
    public String getLeaderImg() {
        return serverSkuList.get(0).getImg();
    }

    //
    public String getLeaderTitle() {
        return serverSkuList.get(0).getTitle();
    }

    //
    public Integer getTotalCount() {
        return orderDTO.getSkuInfoList().stream()
                        .map(sku -> sku.getCount())
                        .reduce(Integer::sum)
                        .orElse(0);
    }

    public void isOk() {
        // 服务端计算出来的当前订单原价
        BigDecimal serverTotalPrice = new BigDecimal("0");

        List<SkuOrderBO> skuOrderBOList = new ArrayList<>();

        skuNotOnSale(orderDTO.getSkuInfoList().size(), serverSkuList.size());

        for (int i = 0; i < serverSkuList.size(); i++) {
            Sku sku = serverSkuList.get(i);
            SkuInfoDTO skuInfoDto = orderDTO.getSkuInfoList().get(i);
            // 校验商品是否售罄
            containsSoldOutSku(sku);
            // 校验是否超过库存
            beyondSkuStock(sku, skuInfoDto);
            // 校验是否超出最大购买数量
            beyondMaxSkuLimit(skuInfoDto);
            //
            serverTotalPrice = serverTotalPrice.add(this.calculateSkuOrderPrice(sku, skuInfoDto));
            // 订单中sku的存储列表
            skuOrderBOList.add(new SkuOrderBO(sku, skuInfoDto));
            //  保存到 order.snapItems 中
            orderSkuList.add(new OrderSku(skuInfoDto, sku));


        }

        totalPriceIsOk(orderDTO.getTotalPrice(), serverTotalPrice);

        if (couponChecker != null) {
            couponChecker.isOk();
            couponChecker.canBeUsed(skuOrderBOList, serverTotalPrice);
            couponChecker.finalTotalPriceIsOk(orderDTO.getFinalTotalPrice(), serverTotalPrice);
        }
    }

    /**
     * 前端价格与服务器计算机个比较
     * @param serverTotalPrice 服务端计算的总原价
     * @param orderTotalPrice 前端传过来的价格
     * */
    private void totalPriceIsOk(BigDecimal orderTotalPrice, BigDecimal serverTotalPrice) {
        if (orderTotalPrice.compareTo(serverTotalPrice) != 0) {
            throw new ParameterException(50005);
        }
    }

    private BigDecimal calculateSkuOrderPrice(Sku sku, SkuInfoDTO skuInfoDTO) {
        if (skuInfoDTO.getCount() <= 0) {
            throw new ParameterException(50007);
        }
        return sku.getActualPrice().multiply(new BigDecimal(skuInfoDTO.getCount()));
    }

    /**
     * 判断是否有下架的商品
     * @param count1
     * @param count2
     * */
    private void skuNotOnSale(int count1, int count2) {
        if (count1 != count2) {
            throw new ParameterException(50002);
        }
    }

    /**
     * 是否售罄
     * @param sku
     * */
    private void containsSoldOutSku(Sku sku) {
        if (sku.getStock() == 0) {
            throw new ParameterException(50001);
        }
    }

    /**
     * 是否超卖
     * @param sku 服务器取出来的sku
     * @param skuInfoDTO 前端传过来的
     * */
    private void beyondSkuStock(Sku sku, SkuInfoDTO skuInfoDTO) {
        if (sku.getStock() < skuInfoDTO.getCount()) {
            throw new ParameterException(50003);
        }
    }

    /**
     * 购买数量是否超出最大购买数量
     * @param skuInfoDTO
     * */
    private void beyondMaxSkuLimit(SkuInfoDTO skuInfoDTO) {
        if (skuInfoDTO.getCount() > maxSkuLimit) {
            throw new ParameterException(50004);
        }
    }


}
