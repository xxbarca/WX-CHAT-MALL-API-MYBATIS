package com.ly.imallbatis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity {

    @JsonIgnore
    private Date deleteTime;

    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    private Date createTime;
}
