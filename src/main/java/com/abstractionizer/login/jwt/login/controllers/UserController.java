package com.abstractionizer.login.jwt.login.controllers;

import com.abstractionizer.login.jwt.db.rmdb.entities.User;
import com.abstractionizer.login.jwt.login.businesses.UserBusiness;
import com.abstractionizer.login.jwt.models.bo.CreateUserBo;
import com.abstractionizer.login.jwt.models.bo.LoginBo;
import com.abstractionizer.login.jwt.models.dto.UserInfo;
import com.abstractionizer.login.jwt.responses.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserBusiness userBusiness;

    @PostMapping
    public SuccessResponse<User> register(@RequestBody @Valid CreateUserBo bo){
        return new SuccessResponse<>(userBusiness.create(bo));
    }

    @PostMapping("/login")
    public SuccessResponse<UserInfo> login(@RequestBody @Valid LoginBo bo){
        return new SuccessResponse<>(userBusiness.login(bo));
    }
}
