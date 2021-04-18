package com.abstractionizer.login.jwt.interceptors;

import com.abstractionizer.login.jwt.exceptions.CustomException;
import com.abstractionizer.login.jwt.models.vo.CustomExceptionVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<Object> handleCustomException(CustomException e){
        CustomExceptionVo response = new CustomExceptionVo(e.getCode(), e.getMsg(), e.getDetails());
        return new ResponseEntity<>(response, e.getHttpStatus());
    }

}
