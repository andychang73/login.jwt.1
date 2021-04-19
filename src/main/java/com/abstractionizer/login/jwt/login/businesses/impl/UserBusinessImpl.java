package com.abstractionizer.login.jwt.login.businesses.impl;

import com.abstractionizer.login.jwt.db.rmdb.entities.User;
import com.abstractionizer.login.jwt.enums.ErrorCode;
import com.abstractionizer.login.jwt.exceptions.InvalidCredentialException;
import com.abstractionizer.login.jwt.login.businesses.UserBusiness;
import com.abstractionizer.login.jwt.login.services.UserService;
import com.abstractionizer.login.jwt.models.bo.CreateUserBo;
import com.abstractionizer.login.jwt.models.bo.LoginBo;
import com.abstractionizer.login.jwt.models.dto.UserInfo;
import com.abstractionizer.login.jwt.utils.Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class UserBusinessImpl implements UserBusiness {

    private final UserService userService;
    private final Util util;

    @Override
    public User create(CreateUserBo bo) {
        User user = new User()
                .setUsername(bo.getUsername())
                .setPassword(util.md5(bo.getPassword()));
        return userService.create(user);
    }

    @Override
    public UserInfo login(LoginBo bo) {
        User user = userService.getUserByUsername(bo.getUsername());
        if(!util.md5(bo.getPassword()).equals(user.getPassword())){
            throw new InvalidCredentialException(ErrorCode.INVALID_CREDENTIAL);
        }
        return new UserInfo().setUserId(user.getId()).setUsername(bo.getUsername());

    }
}
