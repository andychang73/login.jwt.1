package com.abstractionizer.login.jwt.exceptions;

import com.abstractionizer.login.jwt.enums.BaseError;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException{
    private HttpStatus httpStatus;
    private String code;
    private String msg;
    private String details;


    public CustomException(BaseError baseError){
        this(baseError, null);
    }

    public CustomException(BaseError baseError, String details){
        this.httpStatus = baseError.getHttpStatus();
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        this.details = details;
    }
}
