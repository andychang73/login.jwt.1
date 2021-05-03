package com.abstractionizer.login.jwt.login.services.impl;

import com.abstractionizer.login.jwt.db.rmdb.entities.User;
import com.abstractionizer.login.jwt.db.rmdb.mappers.UserMapper;
import com.abstractionizer.login.jwt.enums.ErrorCode;
import com.abstractionizer.login.jwt.exceptions.CustomException;
import com.abstractionizer.login.jwt.login.services.UserService;
import com.abstractionizer.login.jwt.models.vo.UserDetailsVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public void updateLastLoginTime(Integer userId, Date time) {
        if(userMapper.updateLastLoginTime(userId, time) != 1){
            throw new CustomException(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void freezeAccount(Integer userId) {
        if(userMapper.updateStatus(userId) != 1){
            throw new CustomException(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public boolean isUserExists(String username) {
        return userMapper.countByUsername(username) > 0;
    }

    @Override
    public void activateAccount(Integer userId) {
        if(userMapper.setIsActivatedInt(userId) != 1){
            throw new CustomException(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public UserDetailsVo getByUserId(Integer userId) {
        return userMapper.selectByUserId(userId);
    }
}
