package com.ly.imallbatis.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
public class Category extends BaseEntity {

    private Long id;
    private String name;
    private String description;
    private Boolean isRoot;
    private String img;
    private Long parentId;

    private Long index;
}
