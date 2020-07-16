package com.ly.imallbatis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpuImg extends BaseEntity {
    private Long id;

    private String img;

    private Long spuId;
}
