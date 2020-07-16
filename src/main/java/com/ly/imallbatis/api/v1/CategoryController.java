package com.ly.imallbatis.api.v1;

import com.ly.imallbatis.exception.http.NotFoundException;
import com.ly.imallbatis.model.Category;
import com.ly.imallbatis.model.GridCategory;
import com.ly.imallbatis.service.CategoryService;
import com.ly.imallbatis.vo.CategoryPureVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/category")
@RestController
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取全部分类
     * */
    @RequestMapping("/all")
    public Map<String, List<CategoryPureVO>> getAll() {
        List<CategoryPureVO> roots = categoryService.findAllCategoryByIsRoot(true);
        List<CategoryPureVO> subs = categoryService.findAllCategoryByIsRoot(false);

        Map<String, List<CategoryPureVO>> listMap = new HashMap<>();
        listMap.put("roots", roots);
        listMap.put("subs", subs);
        return listMap;
    }

    @RequestMapping("/grid/all")
    public List<GridCategory> getGridCategory() {
        List<GridCategory> gridCategoryList = categoryService.getGridCategory();
        if (gridCategoryList.isEmpty()) {
            throw new NotFoundException(30009);
        }
        return gridCategoryList;
    }
}
