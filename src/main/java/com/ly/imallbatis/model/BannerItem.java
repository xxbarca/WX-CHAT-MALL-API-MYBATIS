package com.ly.imallbatis.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BannerItem extends BaseEntity {

    private Long id;
    private String img;
    private String name;
    private Long bannerId;
    private String keyword;
    private short type;
}
