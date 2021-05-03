package com.abstractionizer.login.jwt.login.services;

import com.abstractionizer.login.jwt.db.rmdb.entities.User;
import com.abstractionizer.login.jwt.models.vo.UserDetailsVo;

import java.util.Date;

public interface UserService {

    User create(User user);

    User getUserByUsername(String username);

    void updateLastLoginTime(Integer userId, Date time);

    void freezeAccount(Integer userId);

    boolean isUserExists(String username);

    void activateAccount(Integer userId);

    UserDetailsVo getByUserId(Integer userId);
}
