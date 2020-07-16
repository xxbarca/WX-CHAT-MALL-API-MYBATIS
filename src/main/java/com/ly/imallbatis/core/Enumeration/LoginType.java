package com.ly.imallbatis.core.Enumeration;

public enum  LoginType {
    USER_WX(0, "微信登陆"),
    USER_EMAIL(1, "邮箱登陆");

    private Integer value;
    private String description;

    LoginType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }
}
