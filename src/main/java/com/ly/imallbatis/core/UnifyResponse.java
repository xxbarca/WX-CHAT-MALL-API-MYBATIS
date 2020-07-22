package com.ly.imallbatis.core;

import com.ly.imallbatis.exception.CreateSuccess;

public class UnifyResponse {

    private int code;
    private String message;
    private String request;

    public UnifyResponse(int code, String message, String request) {
        this.code = code;
        this.message = message;
        this.request = request;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getRequest() {
        return request;
    }

    public static void createSuccess(int code) {
        throw new CreateSuccess(code);
    }
}
