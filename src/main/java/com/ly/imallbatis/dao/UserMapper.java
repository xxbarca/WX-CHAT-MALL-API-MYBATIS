package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findByOpenid(@Param("openid") String openid);

    User findByEmail(@Param("email") String email);

    void save(User user);

    User getUserById(@Param("id") Long id);

}
