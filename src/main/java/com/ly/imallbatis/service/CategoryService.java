package com.ly.imallbatis.service;

import com.ly.imallbatis.model.Category;
import com.ly.imallbatis.model.GridCategory;
import com.ly.imallbatis.vo.CategoryPureVO;

import java.util.List;

public interface CategoryService {

    List<CategoryPureVO> findAllCategoryByIsRoot(Boolean isRoot);

    List<GridCategory> getGridCategory();
}
