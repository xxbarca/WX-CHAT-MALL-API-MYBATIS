package com.ly.imallbatis.vo;

import com.ly.imallbatis.model.Coupon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CouponPureVo {
    private Long id;

    // 外键 活动之间的联系
    private Long activity_id;
    private String title;
    private Date start_time;
    private Date end_time;
    private String description;
    // 满减券 满1000减200， fullMoney -> 1000
    private BigDecimal full_money;
    // 满减券 满1000减200， minus -> 200
    private BigDecimal minus;
    // 折扣券 满1000 打五折 rate -> 五折
    private BigDecimal rate;
    // 对于优惠券的说明, 女装, 牛仔裤, 一种标识
    private String remark;
    // 是否是全场券
    private Boolean whole_store;
    //
    private Integer type;

    public CouponPureVo(Coupon coupon) {
        this.id = coupon.getId();
        this.activity_id = coupon.getActivityId();
        this.title = coupon.getTitle();
        this.start_time = coupon.getStartTime();
        this.end_time = coupon.getEndTime();
        this.description = coupon.getDescription();
        this.full_money = coupon.getFullMoney();
        this.minus = coupon.getMinus();
        this.rate = coupon.getRate();
        this.remark = coupon.getRemark();
        this.whole_store = coupon.getWholeStore();
        this.type = coupon.getType();
//        BeanUtils.copyProperties(coupon, this);
    }

    public static List<CouponPureVo> getList(List<Coupon> coupons) {
        return coupons.stream().map(CouponPureVo::new).collect(Collectors.toList());
    }
}
