package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findByOpenid(@Param("openid") String openid);

    void save(User user);

    User getUserById(@Param("id") Long id);

}
