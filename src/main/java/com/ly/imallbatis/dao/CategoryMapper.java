package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.Category;
import com.ly.imallbatis.model.GridCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {

    List<Category> findAllCategoryByIsRoot(Boolean isRoot);
    List<GridCategory> getGridCategory();
}
