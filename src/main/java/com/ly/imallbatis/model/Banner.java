package com.ly.imallbatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Banner extends BaseEntity {

    private Long id;
    private String name;
    private String description;
    private String title;
    private String img;

    // 一个banner拥有多个bannerItem
    private List<BannerItem> items;
}
