package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.UserCoupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserCouponMapper {

    UserCoupon getByUserIdAndCouponId(@Param("map") Map<String, Object> map);

    /**
     * 保存
     * */
    void save(UserCoupon userCoupon);

    /**
     * 核销优惠券
     * @param couponId 优惠券id
     * @param uid 用户id
     * @param oid 订单id
     * */
    int writeOffCoupon(@Param("couponId") Long couponId, @Param("oid") Long oid, @Param("uid") Long uid);

}
