package com.abstractionizer.login.jwt.models.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomExceptionVo {
    private String code;
    private String msg;
    private String details;
}
