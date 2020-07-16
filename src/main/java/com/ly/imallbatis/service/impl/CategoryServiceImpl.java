package com.ly.imallbatis.service.impl;

import com.ly.imallbatis.dao.CategoryMapper;
import com.ly.imallbatis.model.Category;
import com.ly.imallbatis.model.GridCategory;
import com.ly.imallbatis.service.CategoryService;
import com.ly.imallbatis.vo.CategoryPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryPureVO> findAllCategoryByIsRoot(Boolean isRoot) {
        List<Category> categoryList = categoryMapper.findAllCategoryByIsRoot(isRoot);
        List<CategoryPureVO> categoryPureVOList = categoryList.stream()
                    .map(r -> {
                        return new CategoryPureVO(r);
                    }).collect(Collectors.toList());
        return categoryPureVOList;
    }

    @Override
    public List<GridCategory> getGridCategory() {
        return categoryMapper.getGridCategory();
    }
}
