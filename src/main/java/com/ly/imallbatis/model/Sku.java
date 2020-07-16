package com.ly.imallbatis.model;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ly.imallbatis.util.GenericAndJson;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class Sku extends BaseEntity {
    private Long id;

    private BigDecimal price;
    private BigDecimal discountPrice;
    private Boolean online;
    private String img;
    private String title;
    private Long spuId;
    private Long categoryId;
    private Long rootCategoryId;

//    private List<Spec> specs;
    private String specs;
    private String code;

    private Long stock;

}
