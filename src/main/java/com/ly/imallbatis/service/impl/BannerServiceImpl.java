package com.ly.imallbatis.service.impl;

import com.ly.imallbatis.dao.BannerMapper;
import com.ly.imallbatis.model.Banner;
import com.ly.imallbatis.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Banner getByName(String name) {
        return bannerMapper.getByName(name);
    }
}
