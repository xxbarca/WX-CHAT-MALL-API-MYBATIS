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

    /**
     * 领取一张优惠券
     * @param {uid} 用户id
     * @param {cid} 优惠券id
     * */
    void collectOneCoupon(Long uid, Long cid);
}
