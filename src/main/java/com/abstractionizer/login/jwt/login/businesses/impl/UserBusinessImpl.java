package com.abstractionizer.login.jwt.login.businesses.impl;

import com.abstractionizer.login.jwt.constant.RedisConstant;
import com.abstractionizer.login.jwt.db.rmdb.entities.User;
import com.abstractionizer.login.jwt.enums.ErrorCode;
import com.abstractionizer.login.jwt.exceptions.CustomException;
import com.abstractionizer.login.jwt.exceptions.InvalidCredentialException;
import com.abstractionizer.login.jwt.login.businesses.UserBusiness;
import com.abstractionizer.login.jwt.login.services.UserService;
import com.abstractionizer.login.jwt.login.services.UserTokenService;
import com.abstractionizer.login.jwt.models.bo.CreateUserBo;
import com.abstractionizer.login.jwt.models.bo.LoginBo;
import com.abstractionizer.login.jwt.models.dto.UserInfo;
import com.abstractionizer.login.jwt.models.vo.UserDetailsVo;
import com.abstractionizer.login.jwt.utils.RedisUtil;
import com.abstractionizer.login.jwt.utils.Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@Service
public class UserBusinessImpl implements UserBusiness {

    private final UserTokenService userTokenService;
    private final UserService userService;
    private final RedisUtil redisUtil;
    private final Util util;

    @Override
    public String create(CreateUserBo bo) {
        if(userService.isUserExists(bo.getUsername())){
            throw new CustomException(ErrorCode.USERNAME_EXISTS);
        }
        User user = new User()
                .setUsername(bo.getUsername())
                .setPassword(util.md5(bo.getPassword()))
                .setEmail(bo.getEmail())
                .setPhone(bo.getPhone());

        User registeredUser = userService.create(user);
        String uuid = UUID.randomUUID().toString();
        String validationUrl = getValidationUrl(uuid);
        redisUtil.set(RedisConstant.getUserRegistrationToken(uuid), registeredUser, 1L, TimeUnit.MINUTES);

        return validationUrl;
    }


    @Override
    public UserInfo login(LoginBo bo) {
        User user = isAccountValid(bo.getUsername());

        checkPassword(user, bo.getPassword());

        final String token = userTokenService.generateToken(user.getUsername()).orElseThrow(()-> new RuntimeException("Failed to generate token"));
        Optional<String> oldToken = userTokenService.getOldTokenIfExists(token);
        oldToken.ifPresent(t -> userTokenService.deleteToken(RedisConstant.getUserLoginToken(t)));

        UserInfo userInfo = new UserInfo()
                .setUserId(user.getId())
                .setUsername(user.getUsername())
                .setToken(token);

        userTokenService.setToken(token, userInfo);
        userService.updateLastLoginTime(user.getId(), new Date());
        return userInfo;
    }

    @Override
    public UserDetailsVo activateAccount(String token) {
        User user = redisUtil.get(RedisConstant.getUserRegistrationToken(token), User.class);
        if(user == null){
            throw new CustomException(ErrorCode.ACCOUNT_VALIDATION_EXPIRED);
        }
        userService.activateAccount(user.getId());
        return userService.getByUserId(user.getId());
    }

    private void checkPassword(User user, String password){
        if(!util.md5(user.getPassword()).equals(password)){
            if(countLoginFailure(user.getUsername()) > 3){
                userService.freezeAccount(user.getId());
                throw new CustomException(ErrorCode.ACCOUNT_FROZEN);
            }
            throw new InvalidCredentialException(ErrorCode.INVALID_CREDENTIAL);
        }
    }

    private User isAccountValid(String username){
        User user = userService.getUserByUsername(username);
        if(user.getStatus() != 1){
            throw new CustomException(ErrorCode.ACCOUNT_FROZEN);
        }
        if(!user.isActivated()){
            throw new CustomException(ErrorCode.ACCOUNT_NOT_VALIDATED);
        }

        return user;
    }

    private long countLoginFailure(String username){
        String key = RedisConstant.getUserLoginFailureCount(username);
        long count = 1L;
        long loginFailureCountDuration = 1L;

        if(!redisUtil.isKeyExists(key)){
            redisUtil.set(key, count, loginFailureCountDuration, TimeUnit.MINUTES);
        }else {
            count = redisUtil.increment(key, count);
        }
        log.info("[User Login][{}] login failure count: {}", username, count);
        return count;
    }

    private String getValidationUrl(String uuid){
        return "http://localhost:8080/api/user?token=" + uuid;
    }

}
