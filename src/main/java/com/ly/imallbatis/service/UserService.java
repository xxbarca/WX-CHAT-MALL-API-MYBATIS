package com.ly.imallbatis.service;

import com.ly.imallbatis.model.User;

import java.util.Map;

public interface UserService {

    User getUserById(Long id);

    void updateUserWxInfo(Map<String,Object> user);

}
