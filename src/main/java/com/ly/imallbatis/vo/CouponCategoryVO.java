package com.ly.imallbatis.vo;

import com.ly.imallbatis.model.Category;
import com.ly.imallbatis.model.Coupon;

import java.util.ArrayList;
import java.util.List;

public class CouponCategoryVO extends CouponPureVo {
    private List<CategoryPureVO> categories = new ArrayList<>();

    public CouponCategoryVO(Coupon coupon) {
        super(coupon);

        List<Category> categories = coupon.getCategoryList();

        categories.forEach(category -> {
            CategoryPureVO vo = new CategoryPureVO(category);
            this.categories.add(vo);
        });
    }
}
