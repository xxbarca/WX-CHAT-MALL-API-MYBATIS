package com.ly.imallbatis.service.impl;

import com.ly.imallbatis.dao.ActivityMapper;
import com.ly.imallbatis.model.Activity;
import com.ly.imallbatis.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public Activity getByName(String name) {
        return activityMapper.getByName(name);
    }
}
