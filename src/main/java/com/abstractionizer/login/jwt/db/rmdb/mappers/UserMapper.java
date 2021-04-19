package com.abstractionizer.login.jwt.db.rmdb.mappers;

import com.abstractionizer.login.jwt.db.rmdb.entities.User;
import com.abstractionizer.login.jwt.models.dto.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    Optional<User> selectByUsername(@Param("username") String username);
}
