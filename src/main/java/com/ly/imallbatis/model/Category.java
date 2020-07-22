package com.ly.imallbatis.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Category extends BaseEntity {

    private Long id;
    private String name;
    private String description;
    private Boolean isRoot;
    private String img;
    private Long parentId;

    private Long index;
}
