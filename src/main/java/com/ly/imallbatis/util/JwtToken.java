package com.ly.imallbatis.util;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWT;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtToken {
    private static String jwtKey;

    private static Integer expiredTimeIn;

    private static Integer defaultScope = 8;

    @Value("${missyou.security.jwt-key}")
    public void setJwtKey(String jwtKey) {
        JwtToken.jwtKey = jwtKey;
    }

    @Value("${missyou.security.token-expire-in}")
    public void setExpiredTimeIn(Integer expiredTimeIn) {
        JwtToken.expiredTimeIn = expiredTimeIn;
    }

    /**
     * 获取生成token时填写的claim
     * */
    public static Optional<Map<String, Claim>> getClaims(String token) {
        DecodedJWT decodedJWT;
        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        try {
            decodedJWT = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }

        return Optional.of(decodedJWT.getClaims());

    }

    /**
     * uid 和 scope 写入 jwt
     * @param {uid} 用户ID
     * */
    public static String makeToken(Long uid) {
        return JwtToken.getToken(uid, JwtToken.defaultScope);
    }

    /**
     * @{param} scope: 权限分级数字
     * */
    public static String makeToken(Long uid, Integer scope) {
        return JwtToken.getToken(uid, scope);
    }

    /**
     * @param scope: 权限分级数字
     * @param uid: 用户id
     * */
    private static String getToken(Long uid, Integer scope) {

        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);

        Map<String, Date> map = JwtToken.calculateExpiredIssues();

        String token = JWT.create()
                .withClaim("uid", uid)
                .withClaim("scope", scope)
                .withClaim("userName", 7)
                .withExpiresAt(map.get("expiredTime"))
                .withIssuedAt(map.get("now"))
                .sign(algorithm);

        return token;
    }

    /**
     * 到期时间
     * */
    private static Map<String, Date> calculateExpiredIssues() {
        Map<String, Date> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.SECOND, JwtToken.expiredTimeIn);
        map.put("now", now);
        map.put("expiredTime", calendar.getTime());
        return map;
    }

    /**
     * 验证jwt
     * */
    public static Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }
}
