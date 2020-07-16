package com.ly.imallbatis.vo;

import com.ly.imallbatis.model.Activity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ActivityCouponVo extends ActivityPureVo {

    private List<CouponPureVo> coupons;

    public ActivityCouponVo(Activity activity) {
        super(activity);

        this.coupons = activity.getCouponList().stream().map(coupon -> {
            CouponPureVo couponPureVo = new CouponPureVo(coupon);
            return couponPureVo;
        }).collect(Collectors.toList());
    }
}
