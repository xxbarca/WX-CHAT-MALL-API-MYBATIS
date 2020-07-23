package com.ly.imallbatis.service;

import com.ly.imallbatis.model.Sku;

import java.util.List;

public interface SkuService {

    List<Sku> getSkuListByIds(List<Long> ids);
}
