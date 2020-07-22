package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityMapper {

    Activity getByName(@Param("name") String name);

    Activity getByCouponId(Long cid);
}
