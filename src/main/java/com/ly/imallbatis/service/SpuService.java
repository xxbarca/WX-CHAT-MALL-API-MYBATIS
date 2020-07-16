package com.ly.imallbatis.service;

import com.github.pagehelper.PageInfo;
import com.ly.imallbatis.model.Spu;
import com.ly.imallbatis.vo.SpuSimplifyVO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SpuService {

    Spu getSpu(Long id);

    PageInfo<SpuSimplifyVO> getLatestPagingSpu(Integer pageNum, Integer pageSize);

    PageInfo<Spu> getByCategory(Long cid, Boolean isRoot, Integer pageNum, Integer size);
}
