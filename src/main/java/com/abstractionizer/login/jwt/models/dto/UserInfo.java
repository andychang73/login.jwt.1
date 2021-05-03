package com.abstractionizer.login.jwt.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserInfo implements Serializable {
    private Integer userId;
    private String username;
    private String token;
}
