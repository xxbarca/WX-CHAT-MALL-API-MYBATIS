package com.ly.imallbatis.api.v1;

import com.ly.imallbatis.core.Enumeration.CouponStatus;
import com.ly.imallbatis.core.LocalUser;
import com.ly.imallbatis.core.UnifyResponse;
import com.ly.imallbatis.core.interceptors.ScopeLevel;
import com.ly.imallbatis.model.Coupon;
import com.ly.imallbatis.model.User;
import com.ly.imallbatis.service.CouponService;
import com.ly.imallbatis.vo.CouponCategoryVO;
import com.ly.imallbatis.vo.CouponPureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 获取我的优惠券
     * @param status 优惠券状态
     * */
    @ScopeLevel()
    @GetMapping("/myself/by/status/{status}")
    public List<CouponPureVo> getMyCouponByStatus(@PathVariable Integer status) {
        System.out.println(LocalUser.getUser().getId());
        Long uid = LocalUser.getUser().getId();
        List<Coupon> couponList = null;
        switch (CouponStatus.toType(status)) {
            case AVAILABLE:
                couponList = couponService.getAvailableCoupons(uid);
                break;
            case USED:
                couponList = couponService.getUserCoupons(uid);
                break;
            case EXPIRED:
                couponList = couponService.getExpiredCoupons(uid);
                break;
            default:
                break;
        }
        return CouponPureVo.getList(couponList);
    }

    /**
     * 获取我的可以使用的优惠券(带分类)
     * */
    @ScopeLevel()
    @GetMapping("/myself/available/with_category")
    public List<Coupon> getUserCouponWithCategory() {
        User user = LocalUser.getUser();
        List<Coupon> coupons = couponService.getAvailableCoupons(user.getId());
        if (coupons.isEmpty()) {
            return Collections.emptyList();
        }
        return coupons;
//        return coupons.stream().map(coupon -> {
//            CouponCategoryVO vo = new CouponCategoryVO(coupon);
//            return vo;
//        }).collect(Collectors.toList());
    }
}
