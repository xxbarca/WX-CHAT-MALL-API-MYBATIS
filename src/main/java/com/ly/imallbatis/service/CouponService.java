package com.ly.imallbatis.service;

import com.ly.imallbatis.model.Coupon;

import java.util.List;

public interface CouponService {

    List<Coupon> getByCategory(Long id);
}
