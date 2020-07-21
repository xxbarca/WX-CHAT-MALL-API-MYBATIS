package com.ly.imallbatis.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.imallbatis.dao.UserMapper;
import com.ly.imallbatis.exception.http.ParameterException;
import com.ly.imallbatis.model.User;
import com.ly.imallbatis.service.WxAuthenticationService;
import com.ly.imallbatis.util.JwtToken;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.ibatis.builder.ParameterExpression;
import org.hibernate.procedure.ParameterMisuseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class WxAuthenticationServiceImpl implements WxAuthenticationService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${wx.code2session}")
    private String code2session;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    /**
     * 1. 访问微信服务器验证code嘛是否合法
     * 2. 生成jwt令牌
     * */
    public String code2Session(String code) {
        String url = MessageFormat.format(this.code2session, this.appid, this.appsecret, code);
        RestTemplate restTemplate = new RestTemplate();
        String sessionText = restTemplate.getForObject(url, String.class);
        Map<String, Object> session = new HashMap<>();
        try {
            session = objectMapper.readValue(sessionText, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return this.registerUser(session);
    }

    /**
     * 1. code 换取 用户openid
     * 2. openid 查用户
     * 3. 未注册 -> openid 写入 user / 已注册 -> 查询用户信息
     * 4. -> uid
     * 5. uid写入jwt
     * 6. jwt -> 小程序
     * */
    private String registerUser(Map<String, Object> session) {
        String openid = (String) session.get("openid");
        if (openid == null) {
            throw new ParameterException(20004);
        }

        User user = userMapper.findByOpenid(openid);
        if (user == null) {
            user = User.builder().openid(openid).build();
            userMapper.save(user);
        }
        return JwtToken.makeToken(user.getId());
    }

}
