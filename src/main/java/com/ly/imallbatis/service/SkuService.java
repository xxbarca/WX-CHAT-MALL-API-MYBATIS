package com.ly.imallbatis.service;

import com.ly.imallbatis.model.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuService {

    List<Sku> getSkuListByIds(List<Long> ids);

    int reduceStock(@Param("sid") Long sid, @Param("quantity") Long quantity);
}
