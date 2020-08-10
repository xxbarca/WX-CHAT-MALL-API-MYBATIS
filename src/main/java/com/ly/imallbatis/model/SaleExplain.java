package com.ly.imallbatis.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SaleExplain extends BaseEntity {

    private Long id;
    private int fixed;
    private String text;
    private Long spuId;
    private int index;
    private Long replaceId;
}
