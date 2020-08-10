package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.SaleExplain;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleExplainMapper {

    List<SaleExplain> findAll();
}
