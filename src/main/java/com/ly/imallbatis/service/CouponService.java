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

    /**
     *  获取我的可以使用的优惠券
     * @param uid 用户id
     * */
    List<Coupon> getAvailableCoupons(Long uid);

    /**
     * 获取我的已经使用了的优惠券
     * @param uid 用户id
     * */
    List<Coupon> getUserCoupons(Long uid);

    /**
     * 获取我的已经过期了的优惠券
     * @param uid 用户id
     * */
    List<Coupon> getExpiredCoupons(Long uid);
}
