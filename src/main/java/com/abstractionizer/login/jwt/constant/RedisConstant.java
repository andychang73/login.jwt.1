package com.abstractionizer.login.jwt.constant;

import lombok.NonNull;

public class RedisConstant {

    public static final String USER_LOGIN_TOKEN = "LOGIN:JWT:TOKEN:%s";
    public static final String USER_LOGIN_FAILURE_COUNT = "LOGIN:USER:FAILURE:%s";
    public static final String USER_REGISTRATION_TOKEN = "REGISTRATION:USER:TOKEN:%s";

    public static String getUserLoginToken(@NonNull final String token){
        return String.format(USER_LOGIN_TOKEN, token);
    }

    public static String getUserLoginFailureCount(@NonNull final String username){
        return String.format(USER_LOGIN_FAILURE_COUNT, username);
    }

    public static String getUserRegistrationToken(@NonNull final String uuid){
        return String.format(USER_REGISTRATION_TOKEN, uuid);
    }
}
