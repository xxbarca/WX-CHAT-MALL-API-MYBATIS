package com.ly.imallbatis.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GridCategory extends BaseEntity {

    private Long id;

    private String title;
    private String img;
    private String name;

    private Long categoryId;
    private Long rootCategoryId;
}
