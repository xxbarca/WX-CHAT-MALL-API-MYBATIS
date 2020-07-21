package com.ly.imallbatis.api.v1;

import com.ly.imallbatis.model.Coupon;
import com.ly.imallbatis.service.CouponService;
import com.ly.imallbatis.vo.CouponPureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 服务于商品详情页
     * */
    @GetMapping("/by/category/{cid}")
    public List<Coupon> getCouponListByCategory(@PathVariable Long cid) {
        return couponService.getByCategory(cid);
    }


    /**
     * 获取全场券
     * */
    @GetMapping("/whole_store")
    public List<CouponPureVo> getWholeStoreCouponList() {
        List<Coupon> couponList = couponService.getWholeStoreCoupons();
        if (couponList.isEmpty()) {
            return Collections.emptyList();
        }
        return CouponPureVo.getList(couponList);

    }

}
