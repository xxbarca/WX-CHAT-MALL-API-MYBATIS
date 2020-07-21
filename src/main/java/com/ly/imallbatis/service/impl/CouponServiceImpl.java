package com.ly.imallbatis.service.impl;

import com.ly.imallbatis.dao.CouponMapper;
import com.ly.imallbatis.model.Coupon;
import com.ly.imallbatis.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponMapper couponMapper;
    @Override
    public List<Coupon> getByCategory(Long id) {
        return couponMapper.getByCategory(id);
    }

    @Override
    public List<Coupon> getWholeStoreCoupons() {
        return couponMapper.getWholeStoreCoupons();
    }


}
