package com.ly.imallbatis.core.Enumeration;

public enum CouponStatus {

    AVAILABLE(1, "可使用, 未过期"),
    USED(2, "已使用"),
    EXPIRED(3, "未使用, 已过期");

    private Integer value;
    private String description;

    public Integer getValue() {
        return this.value;
    }

    CouponStatus(Integer value, String description) {
        this.value = value;
    }
}
