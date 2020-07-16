package com.ly.imallbatis.service;

import com.ly.imallbatis.model.Theme;

import java.util.List;
import java.util.Optional;

public interface ThemeServices {

    List<Theme> findByNames(List<String> names);

    Theme findByName(String themeName);

}
