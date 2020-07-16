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

    private String registerUser(Map<String, Object> session) {
        String openid = (String) session.get("openid");
        if (openid == null) {
            throw new ParameterException(20004);
        }

        User user = userMapper.findByOpenid(openid);
        if (user != null) {
            Long id = user.getId();
            return JwtToken.makeToken(user.getId());
        } else {
            User user1 = User.builder().openid(openid).build();
            userMapper.save(user1);
            Long uid = user.getId();
            return JwtToken.makeToken(uid);
        }
    }

}
