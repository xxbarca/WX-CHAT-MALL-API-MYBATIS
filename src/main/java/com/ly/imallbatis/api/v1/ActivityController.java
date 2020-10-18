package com.ly.imallbatis.api.v1;


import com.ly.imallbatis.exception.http.NotFoundException;
import com.ly.imallbatis.model.Activity;
import com.ly.imallbatis.service.ActivityService;
import com.ly.imallbatis.vo.ActivityCouponVo;
import com.ly.imallbatis.vo.ActivityPureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/activity")
@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/name/{name}")
    public ActivityPureVo getHomeActivity(@PathVariable String name) {
        Activity activity = activityService.getByName(name);
        if (activity == null) {
            throw new NotFoundException(40001);
        }
        ActivityPureVo activityPureVo = new ActivityPureVo(activity);
        return activityPureVo;
    }

    /**
     * 对应优惠券列表页面
     * */
    @GetMapping("/name/{name}/with_coupon")
    public ActivityCouponVo getActivityWithCoupon(@PathVariable String name) {
        Activity activity = activityService.getByName(name);
        if (activity == null) {
            throw new NotFoundException(40001);
        }
        ActivityCouponVo activityCouponVo = new ActivityCouponVo(activity);
        return activityCouponVo;
    }

}
