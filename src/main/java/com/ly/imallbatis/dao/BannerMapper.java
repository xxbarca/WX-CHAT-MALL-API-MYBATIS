package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.Banner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerMapper {

    Banner getByName(@Param("name") String name);
}
