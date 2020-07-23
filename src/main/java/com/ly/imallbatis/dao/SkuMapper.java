package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.Sku;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkuMapper {
    List<Sku> getSkuListByIds(@Param("ids") List<Long> ids);
}
