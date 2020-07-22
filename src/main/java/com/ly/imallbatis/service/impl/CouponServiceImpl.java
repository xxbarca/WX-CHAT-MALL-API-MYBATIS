package com.ly.imallbatis.service.impl;

import com.ly.imallbatis.core.Enumeration.CouponStatus;
import com.ly.imallbatis.dao.ActivityMapper;
import com.ly.imallbatis.dao.CouponMapper;
import com.ly.imallbatis.dao.UserCouponMapper;
import com.ly.imallbatis.exception.http.NotFoundException;
import com.ly.imallbatis.exception.http.ParameterException;
import com.ly.imallbatis.model.Activity;
import com.ly.imallbatis.model.Coupon;
import com.ly.imallbatis.model.UserCoupon;
import com.ly.imallbatis.service.CouponService;
import com.ly.imallbatis.util.CommonUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private UserCouponMapper userCouponMapper;

    @Override
    public List<Coupon> getByCategory(Long id) {
        return couponMapper.getByCategory(id);
    }

    @Override
    public List<Coupon> getWholeStoreCoupons() {
        return couponMapper.getWholeStoreCoupons();
    }

    @Override
    public void collectOneCoupon(Long uid, Long cid) {
        // 判断该cid是否真的对应一张优惠券
        Coupon coupon = couponMapper.getCouponById(cid);
        if (coupon == null) {
            throw new NotFoundException(40003);
        }
        // coupon 领取日期是否过期 -> 判断活动是否过期
        Activity activity = activityMapper.getByCouponId(cid);
        if (activity == null) {
            throw new NotFoundException(40010);
        }

        Date now = new Date();
        Boolean isIn = CommonUtil.isInTimeLine(now, activity.getStart_time(), activity.getEnd_time());
        if (!isIn) {
            throw new ParameterException(40005);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("cid", cid);

        UserCoupon userCoupon = userCouponMapper.getByUserIdAndCouponId(map);
        if (userCoupon != null) {
            throw new ParameterException(40006);
        }

        userCoupon = UserCoupon.builder()
                    .userId(uid)
                    .couponId(cid)
                    .status(CouponStatus.AVAILABLE.getValue())
                    .createTime(now)
                    .build();

        userCouponMapper.save(userCoupon);

    }
}
