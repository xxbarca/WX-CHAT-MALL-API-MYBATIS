package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.UserCoupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserCouponMapper {

    UserCoupon getByUserIdAndCouponId(@Param("map") Map<String, Object> map);

    void save(UserCoupon userCoupon);

}
