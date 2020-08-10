package com.ly.imallbatis.service.impl;

import com.ly.imallbatis.dao.SaleExplainMapper;
import com.ly.imallbatis.model.SaleExplain;
import com.ly.imallbatis.service.SaleExplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleExplainServiceImpl implements SaleExplainService {

    @Autowired
    private SaleExplainMapper saleExplainMapper;

    @Override
    public List<SaleExplain> findAll() {
        return saleExplainMapper.findAll();
    }
}
