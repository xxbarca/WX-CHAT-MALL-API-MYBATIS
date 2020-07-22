package com.ly.imallbatis.service.impl;

import com.ly.imallbatis.dao.UserMapper;
import com.ly.imallbatis.model.User;
import com.ly.imallbatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }
}
