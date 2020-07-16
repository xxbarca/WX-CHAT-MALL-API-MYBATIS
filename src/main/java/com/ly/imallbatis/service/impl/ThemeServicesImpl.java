package com.ly.imallbatis.service.impl;

import com.ly.imallbatis.dao.ThemeMapper;
import com.ly.imallbatis.model.Theme;
import com.ly.imallbatis.service.ThemeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeServicesImpl implements ThemeServices {

    @Autowired
    private ThemeMapper themeMapper;


    @Override
    public List<Theme> findByNames(List<String> names) {
        return themeMapper.findByNames(names);
    }

    @Override
    public Theme findByName(String themeName) {
        return themeMapper.findByName(themeName);
    }
}
