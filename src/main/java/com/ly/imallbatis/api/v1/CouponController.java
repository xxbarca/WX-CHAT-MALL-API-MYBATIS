package com.ly.imallbatis.api.v1;

import com.ly.imallbatis.core.LocalUser;
import com.ly.imallbatis.core.UnifyResponse;
import com.ly.imallbatis.core.interceptors.ScopeLevel;
import com.ly.imallbatis.exception.CreateSuccess;
import com.ly.imallbatis.model.Coupon;
import com.ly.imallbatis.service.CouponService;
import com.ly.imallbatis.vo.CouponPureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 用户领取优惠券
     * @param cid 优惠券id
     * */
    @ScopeLevel()
    @PostMapping("/collect/{cid}")
    public void collectCoupon(@PathVariable Long cid) {
        Long uid = LocalUser.getUser().getId();
        couponService.collectOneCoupon(uid, cid);
        UnifyResponse.createSuccess(0);
    }

}
