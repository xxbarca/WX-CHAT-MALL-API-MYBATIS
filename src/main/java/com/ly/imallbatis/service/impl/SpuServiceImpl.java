package com.ly.imallbatis.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.imallbatis.dao.SpuMapper;
import com.ly.imallbatis.model.Sku;
import com.ly.imallbatis.model.Spec;
import com.ly.imallbatis.model.Spu;
import com.ly.imallbatis.service.SpuService;
import com.ly.imallbatis.vo.SpuSimplifyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Override
    public Spu getSpu(Long id) {
        Spu spu = spuMapper.getSpu(id);

        return spu;
    }
    
    public PageInfo<SpuSimplifyVO> getLatestPagingSpu(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Spu> spuList = spuMapper.getLatestPagingSpu();
        List<SpuSimplifyVO> spuSimplifyVOS = spuList.stream().map(s -> {
            SpuSimplifyVO spuSimplifyVO = new SpuSimplifyVO();
            BeanUtils.copyProperties(s, spuSimplifyVO);
            return spuSimplifyVO;
        }).collect(Collectors.toList());
        PageInfo<SpuSimplifyVO> pageInfo = new PageInfo<>(spuSimplifyVOS);
        return pageInfo;
    }

    @Override
    public PageInfo<Spu> getByCategory(Long cid, Boolean isRoot, Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<Spu> spuList = null;
        if (isRoot) {
            spuList = spuMapper.findByRootCategoryId(cid);
        } else {
            spuList = spuMapper.findByCategoryId(cid);
        }
        PageInfo<Spu> pageInfo = new PageInfo<>(spuList);
        return pageInfo;
    }
}
