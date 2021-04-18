package com.abstractionizer.login.jwt.enums;

import org.springframework.http.HttpStatus;

public interface BaseError {

    HttpStatus getHttpStatus();

    String getCode();

    String getMsg();
}
