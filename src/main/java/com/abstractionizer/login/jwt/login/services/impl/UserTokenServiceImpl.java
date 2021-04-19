package com.abstractionizer.login.jwt.login.services.impl;

import com.abstractionizer.login.jwt.constant.RedisConstant;
import com.abstractionizer.login.jwt.login.services.UserTokenService;
import com.abstractionizer.login.jwt.models.dto.UserInfo;
import com.abstractionizer.login.jwt.utils.JwtUtil;
import com.abstractionizer.login.jwt.utils.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@Service
public class UserTokenServiceImpl implements UserTokenService {

    public static final long MAX_TOKEN_LIFE_HOUR = 24;
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;

    @Override
    public Optional<String> getOldTokenIfExists(@NonNull final String token) {
        return Optional.ofNullable(redisUtil.get(RedisConstant.getUserLoginToken(token), String.class));
    }

    @Override
    public Optional<String> generateToken(@NonNull final String username) {
        String token;
        int count = 0;
        while(true){
            token = jwtUtil.createToken(username);
            count++;
            if(!redisUtil.isKeyExists(RedisConstant.getUserLoginToken(token))){
                break;
            }
            token = null;
            if(count >= 3){
                break;
            }
        }
        return Optional.ofNullable(token);
    }

    @Override
    public void deleteToken(String token) {
        redisUtil.deleteKey(RedisConstant.getUserLoginToken(token));
    }

    @Override
    public void setToken(String token, UserInfo info) {
        redisUtil.set(RedisConstant.getUserLoginToken(token), info, MAX_TOKEN_LIFE_HOUR, TimeUnit.HOURS);
    }

    @Override
    public boolean isTokenValid(String token, String username) {
        return jwtUtil.isTokenValid(token, username);
    }
}
