package com.ly.imallbatis.exception.http;

import org.apache.ibatis.annotations.Param;

public class ParameterException extends HttpException {
    public ParameterException(int code) {
        this.code = code;
        this.httpStatusCode = 400;
    }
}
