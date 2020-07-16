package com.ly.imallbatis.exception.http;

public class NotFoundException extends HttpException {

    public NotFoundException(int code) {
        this.httpStatusCode = 404;
        this.code = code;
    }

}
