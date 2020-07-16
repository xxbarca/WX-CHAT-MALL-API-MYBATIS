package com.ly.imallbatis.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Theme extends BaseEntity {
    private Long id;

    private String title;
    private String description;
    private String name;
    private String extend;
    private String entranceImg;
    private String internalTopImg;
    private String online;
    private String titleImg;
    private String tplName;

    private List<Spu> spuList;
}
