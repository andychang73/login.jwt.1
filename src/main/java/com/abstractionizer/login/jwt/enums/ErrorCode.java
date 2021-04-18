package com.abstractionizer.login.jwt.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements BaseError{
    ;


    private HttpStatus httpStatus;
    private String code;
    private String msg;

    ErrorCode(HttpStatus httpStatus, String code, String msg){
        this.httpStatus = httpStatus;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
