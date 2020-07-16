package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityMapper {

    Activity getByName(@Param("name") String name);
}
