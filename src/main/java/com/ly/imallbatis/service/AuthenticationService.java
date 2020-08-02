package com.ly.imallbatis.service;

public interface AuthenticationService {
    String code2Session(String code);

    String code2SessionByEmail(String email);

}
