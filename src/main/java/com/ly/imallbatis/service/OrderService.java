package com.ly.imallbatis.service;

import com.github.pagehelper.PageInfo;
import com.ly.imallbatis.dto.OrderDTO;
import com.ly.imallbatis.logic.CouponChecker;
import com.ly.imallbatis.logic.OrderChecker;
import com.ly.imallbatis.model.Order;
import com.ly.imallbatis.vo.OrderSimplifyVo;
import com.ly.imallbatis.vo.SpuSimplifyVO;

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

    /**
     * 获取未支付订单
     * */
    PageInfo<OrderSimplifyVo> getUnPaid(Integer pageNum, Integer pageSize);

    /**
     * 查询其他状态订单
     * @param status 订单状态
     * */
    PageInfo<OrderSimplifyVo> getOrderByStatus(Integer status, Integer pageNum, Integer pageSize);

    /**
     * 根据id获取订单详情
     * */
    Order getOrder(Long oid);
}
