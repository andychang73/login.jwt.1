package com.abstractionizer.login.jwt.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements BaseError{
    DATA_INSERT_UNSUCCESSFUL(HttpStatus.INTERNAL_SERVER_ERROR, "10000", "Data insert unsuccessful"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "10001", "User account does not exist"),
    INVALID_CREDENTIAL(HttpStatus.INTERNAL_SERVER_ERROR, "10002", "Invalid Credential"),
    DATA_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "10003", "Data update failed"),
    ACCOUNT_FROZEN(HttpStatus.INTERNAL_SERVER_ERROR, "10004", "Your account has been frozen, please contact admin"),
    USERNAME_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR, "10005", "Username already exists"),
    ACCOUNT_VALIDATION_EXPIRED(HttpStatus.NOT_FOUND, "10006", "Account validation is expired"),
    ACCOUNT_NOT_VALIDATED(HttpStatus.INTERNAL_SERVER_ERROR, "10007", "Account has not yet been validated")
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
