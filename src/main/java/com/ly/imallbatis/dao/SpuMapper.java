package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.Spu;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpuMapper {

    /**
     * 通过id查询spu
     * */
    Spu getSpu(Long id);

//    List<Spu> getLatestPagingSpu(@Param("start") Integer start, @Param("count") Integer count);
    List<Spu> getLatestPagingSpu();


    /**
     * 查询二级分类
     * @param cid 分类id
     * */
    List<Spu> findByCategoryId(Long cid);

    /**
     * 查询根分类
     * @param id 根id
     * */
    List<Spu> findByRootCategoryId(Long id);

}
