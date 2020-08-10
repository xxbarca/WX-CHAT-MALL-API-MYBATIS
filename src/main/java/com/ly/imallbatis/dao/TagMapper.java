package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {
    List<Tag> getByType(@Param("type") Integer type);
}
