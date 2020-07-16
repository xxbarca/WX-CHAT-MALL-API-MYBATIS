package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.Theme;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThemeMapper {

    List<Theme> findByNames(@Param("names") List<String> names);

    Theme findByName(String name);
}
