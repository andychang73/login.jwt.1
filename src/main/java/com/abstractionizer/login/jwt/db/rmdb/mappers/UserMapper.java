package com.abstractionizer.login.jwt.db.rmdb.mappers;

import com.abstractionizer.login.jwt.db.rmdb.entities.User;
import com.abstractionizer.login.jwt.models.dto.UserInfo;
import com.abstractionizer.login.jwt.models.vo.UserDetailsVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Optional;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    Optional<User> selectByUsername(@Param("username") String username);

    int updateLastLoginTime(@Param("id") Integer userId, @Param("time") Date time);

    int updateStatus(@Param("id") Integer userId);

    int countByUsername(@Param("username") String username);

    int setIsActivatedInt(@Param("id") Integer userId);

    UserDetailsVo selectByUserId(@Param("id") Integer userId);
}
