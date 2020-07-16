package com.ly.imallbatis.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)  // 实体类与json互转的时候 属性值为null的不参与序列化
public class Spu extends BaseEntity {
    private Long id;
    private String title;
    private String subtitle;
    private Long categoryId;
    private Long rootCategoryId;
    private Boolean online;
    private String price;
    private Long sketchSpecId;
    private Long defaultSkuId;
    private String img;
    private String discountPrice;
    private String description;
    private String tags;
    private Boolean isTest;
    private String forThemeImg;
//    private Object spuThemeImg;

    private List<Sku> skuList;
    private List<SpuImg> spuImgList;
    private List<SpuDetailImg> spuDetailImgList;
}
