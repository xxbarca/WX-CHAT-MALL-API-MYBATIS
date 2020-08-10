package com.ly.imallbatis.service.impl;

import com.ly.imallbatis.dao.TagMapper;
import com.ly.imallbatis.model.Tag;
import com.ly.imallbatis.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> getByType(Integer type) {
        return tagMapper.getByType(type);
    }
}
