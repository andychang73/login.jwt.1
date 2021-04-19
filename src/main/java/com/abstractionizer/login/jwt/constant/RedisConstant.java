package com.abstractionizer.login.jwt.constant;

import lombok.NonNull;

public class RedisConstant {

    public static final String USER_LOGIN_TOKEN = "LOGIN:JWT:TOKEN:%s";

    public static String getUserLoginToken(@NonNull final String token){
        return String.format(USER_LOGIN_TOKEN, token);
    }
}
