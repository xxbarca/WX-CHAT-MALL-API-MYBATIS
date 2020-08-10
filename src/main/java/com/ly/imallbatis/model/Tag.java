package com.ly.imallbatis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tag extends BaseEntity {
    private Long id;

    private Integer type;

    private String title;

    private Integer highlight;

    private String description;
}
