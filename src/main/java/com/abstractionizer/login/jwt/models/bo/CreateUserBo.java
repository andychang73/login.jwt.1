package com.abstractionizer.login.jwt.models.bo;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class CreateUserBo {

    @NotEmpty
    private String username;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9])(?=.*[~!@#$%^&*_=])(?=\\S+$).{8,}$", message= "Invalid password")
    private String password;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email address")
    private String email;

    @NotEmpty
    @Pattern(regexp = "09\\d{8}", message="Invalid phone number")
    private String phone;
}
