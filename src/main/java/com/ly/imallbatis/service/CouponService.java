package com.ly.imallbatis.service;

import com.ly.imallbatis.model.Coupon;

import java.util.List;

public interface CouponService {

    /**
     * 根据分类获取coupon
     * */
    List<Coupon> getByCategory(Long id);

    /**
     * 获取全场券
     * */
    List<Coupon> getWholeStoreCoupons();
}
