package com.abstractionizer.login.jwt.models.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsVo {
    private Integer userId;
    private String username;
    private String email;
    private String phone;
    private Integer status;
    private boolean isActivated;
}
