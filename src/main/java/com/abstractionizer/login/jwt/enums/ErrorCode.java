package com.abstractionizer.login.jwt.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements BaseError{
    DATA_INSERT_UNSUCCESSFUL(HttpStatus.INTERNAL_SERVER_ERROR, "10000", "Data insert unsuccessful"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "10001", "User account does not exist"),
    INVALID_CREDENTIAL(HttpStatus.INTERNAL_SERVER_ERROR, "10002", "Invalid Credential")
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
