package com.ly.imallbatis.service;

import com.ly.imallbatis.dto.OrderDTO;
import com.ly.imallbatis.logic.CouponChecker;
import com.ly.imallbatis.logic.OrderChecker;

public interface OrderService {

    /**
     * 订单校验
     * @param uid 用户id
     * @param orderDTO 前端传过来的订单数据
     * */
    OrderChecker isOk(Long uid, OrderDTO orderDTO);

    /**
     * 处理订单
     * @param uid 用户id
     * @param orderDTO 前端传过来的订单数据
     * @param orderChecker 优惠券校验类
     * */
    Long placeOrder(Long uid, OrderDTO orderDTO, OrderChecker orderChecker);
}
