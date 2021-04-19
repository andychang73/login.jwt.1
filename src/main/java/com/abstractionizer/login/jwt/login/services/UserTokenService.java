package com.abstractionizer.login.jwt.login.services;

import com.abstractionizer.login.jwt.models.dto.UserInfo;

import java.util.Optional;

public interface UserTokenService {

    Optional<String> getOldTokenIfExists(String token);

    Optional<String> generateToken(String username);

    void deleteToken(String token);

    void setToken(String token, UserInfo info);

    boolean isTokenValid(String token, String username);
}
