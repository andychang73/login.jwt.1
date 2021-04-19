package com.abstractionizer.login.jwt.models.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginBo {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
