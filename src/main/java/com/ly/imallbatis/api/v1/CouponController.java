package com.ly.imallbatis.api.v1;

import com.ly.imallbatis.model.Coupon;
import com.ly.imallbatis.service.CouponService;
import com.ly.imallbatis.vo.CouponPureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/by/category/{cid}")
    public List<Coupon> getCouponListByCategory(@PathVariable Long cid) {
        return couponService.getByCategory(cid);
    }

}
