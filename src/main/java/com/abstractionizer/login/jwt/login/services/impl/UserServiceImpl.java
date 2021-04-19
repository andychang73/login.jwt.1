package com.abstractionizer.login.jwt.login.services.impl;

import com.abstractionizer.login.jwt.db.rmdb.entities.User;
import com.abstractionizer.login.jwt.db.rmdb.mappers.UserMapper;
import com.abstractionizer.login.jwt.enums.ErrorCode;
import com.abstractionizer.login.jwt.exceptions.CustomException;
import com.abstractionizer.login.jwt.login.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User create(User user) {
        if(userMapper.insert(user) != 1){
            throw new CustomException(ErrorCode.DATA_INSERT_UNSUCCESSFUL);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
