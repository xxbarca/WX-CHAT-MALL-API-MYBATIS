package com.ly.imallbatis.exception;

import com.ly.imallbatis.exception.http.HttpException;

public class CreateSuccess extends HttpException {

    public CreateSuccess(int code) {
        this.httpStatusCode = 201;
        this.code = code;
    }
}
