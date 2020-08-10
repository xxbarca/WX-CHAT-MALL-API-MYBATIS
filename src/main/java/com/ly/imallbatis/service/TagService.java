package com.ly.imallbatis.service;

import com.ly.imallbatis.model.Tag;

import java.util.List;

public interface TagService {

    List<Tag> getByType(Integer type);
}
