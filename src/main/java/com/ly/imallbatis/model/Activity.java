package com.ly.imallbatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Activity extends BaseEntity {

    private Long id;
    private String title;
    private String name;
    private String description;
    private Date start_time;
    private Date end_time;
    private Boolean online;
    private String entrance_img;
    private String internalTopImg;
    private String remark;

    private List<Coupon> couponList;
}
