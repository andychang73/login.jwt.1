package com.abstractionizer.login.jwt.models.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateUserBo {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
