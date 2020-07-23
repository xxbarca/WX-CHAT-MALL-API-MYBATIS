package com.ly.imallbatis.service.impl;

import com.ly.imallbatis.dao.SkuMapper;
import com.ly.imallbatis.model.Sku;
import com.ly.imallbatis.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public List<Sku> getSkuListByIds(List<Long> ids) {
        return skuMapper.getSkuListByIds(ids);
    }
}
