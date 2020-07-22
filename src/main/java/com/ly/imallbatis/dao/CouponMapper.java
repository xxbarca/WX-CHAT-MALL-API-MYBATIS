package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.Coupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface CouponMapper {

    List<Coupon> getByCategory(@Param("id") Long id);

    List<Coupon> getWholeStoreCoupons();

    Coupon getCouponById(Long cid);

}
