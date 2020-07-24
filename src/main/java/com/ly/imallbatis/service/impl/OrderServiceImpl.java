package com.ly.imallbatis.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.imallbatis.core.Enumeration.OrderStatus;
import com.ly.imallbatis.core.money.IMoneyDiscount;
import com.ly.imallbatis.dao.OrderMapper;
import com.ly.imallbatis.dao.UserCouponMapper;
import com.ly.imallbatis.dto.OrderDTO;
import com.ly.imallbatis.dto.SkuInfoDTO;
import com.ly.imallbatis.exception.http.ForbiddenException;
import com.ly.imallbatis.exception.http.NotFoundException;
import com.ly.imallbatis.exception.http.ParameterException;
import com.ly.imallbatis.logic.CouponChecker;
import com.ly.imallbatis.logic.OrderChecker;
import com.ly.imallbatis.model.*;
import com.ly.imallbatis.service.CouponService;
import com.ly.imallbatis.service.OrderService;
import com.ly.imallbatis.service.SkuService;
import com.ly.imallbatis.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SkuService skuService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private UserCouponMapper userCouponMapper;

    @Autowired
    private IMoneyDiscount iMoneyDiscount;

    @Autowired
    private OrderMapper orderMapper;

    @Value("${missyou.order.max-sku-limit}")
    private Integer maxSkuLimit;

    @Value("${missyou.order.pay-time-limit}")
    private Integer payTimeLimit;


    @Override
    public OrderChecker isOk(Long uid, OrderDTO orderDTO) {

        // 判断价格是否小于0
        if (orderDTO.getFinalTotalPrice().compareTo(new BigDecimal("0")) <= 0) {
            throw new ParameterException(50011);
        }

        // 提取订单中的 sku id
        List<Long> skuIdList = orderDTO.getSkuInfoList()
                                .stream()
                                .map(SkuInfoDTO::getId)
                                .collect(Collectors.toList());
        List<Sku> skuList = skuService.getSkuListByIds(skuIdList);
        skuList.stream().forEach(sku -> {
            String specs_temp = sku.getSpecsTemp();
            List<Spec> specList = JSONObject.parseArray(specs_temp, Spec.class);
            sku.setSpecs(specList);
        });
        //
        Long couponId = orderDTO.getCouponId();
        CouponChecker couponChecker = null;
        if (couponId != null) {
            //
            Coupon coupon = couponService.getCouponById(couponId);
            if (coupon == null) {
                throw new NotFoundException(40004);
            }
            //
            Map<String, Object> map = new HashMap<>();
            map.put("uid", uid);
            map.put("cid", couponId);
            map.put("status", 1);
            UserCoupon userCoupon = userCouponMapper.getByUserIdAndCouponId(map);
            if (userCoupon == null) { // 优惠券存在, 当前用户没有领取
                throw new NotFoundException(50006);
            }
            //
            couponChecker = new CouponChecker(coupon, iMoneyDiscount);
        }
        OrderChecker orderChecker = new OrderChecker(orderDTO, skuList, couponChecker, maxSkuLimit);
        orderChecker.isOk();
        return orderChecker;
    }

    @Transactional
    @Override
    public Long placeOrder(Long uid, OrderDTO orderDTO, OrderChecker orderChecker) {
        String orderNo = OrderUtil.makeOrderNo();
        Order order = Order.builder()
                .orderNo(orderNo)
                .totalPrice(orderDTO.getTotalPrice())
                .finalTotalPrice(orderDTO.getFinalTotalPrice())
                .userId(uid)
                .totalCount(orderChecker.getTotalCount().longValue())
                .snapImg(orderChecker.getLeaderImg())
                .snapTitle(orderChecker.getLeaderTitle())
                .status(OrderStatus.UNPAID.value())
                .build();

        String snapAddress = JSON.toJSONString(orderDTO.getAddress());
        order.setSnapAddress(snapAddress);

        String snapItems = JSONArray.toJSONString(orderDTO.getSkuInfoList());
        order.setSnapItems(snapItems);

        orderMapper.save(order);

        //
        reduceStock(orderChecker);
        //
        if (orderDTO.getCouponId() != null) {
            writeOffCoupon(orderDTO.getCouponId(), order.getId(), uid);
        }

        return order.getId();
    }

    /**
     * 订单插入数据库后减库存
     * */
    private void reduceStock(OrderChecker orderChecker) {
        List<OrderSku> orderSkuList = orderChecker.getOrderSkuList();
        for (OrderSku orderSku: orderSkuList) {
            int result = skuService.reduceStock(orderSku.getId(), orderSku.getCount().longValue());
            if (result != 1) {
                throw new ParameterException(50011);
            }
        }
    }

    /**
     * 核销优惠券
     * @param couponId 优惠券id
     * @param uid 用户id
     * @param oid 订单id
     * */
    private void writeOffCoupon(Long couponId, Long oid, Long uid) {
        int result = userCouponMapper.writeOffCoupon(couponId, oid, uid);
        if (result != 1) {
            throw new ForbiddenException(40012);
        }
    }
}
