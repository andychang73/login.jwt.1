package com.abstractionizer.login.jwt.login.controllers;

import com.abstractionizer.login.jwt.login.businesses.UserBusiness;
import com.abstractionizer.login.jwt.models.bo.CreateUserBo;
import com.abstractionizer.login.jwt.models.bo.LoginBo;
import com.abstractionizer.login.jwt.models.dto.UserInfo;
import com.abstractionizer.login.jwt.models.vo.UserDetailsVo;
import com.abstractionizer.login.jwt.responses.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserBusiness userBusiness;

    @PostMapping
    public SuccessResponse<String> register(@RequestBody @Valid CreateUserBo bo){
        return new SuccessResponse<>(userBusiness.create(bo));
    }

    @GetMapping
    public SuccessResponse<UserDetailsVo> validate(@RequestParam("token") String token){
        return new SuccessResponse<>(userBusiness.activateAccount(token));
    }

    @PostMapping("/login")
    public SuccessResponse<UserInfo> login(@RequestBody @Valid LoginBo bo){
        return new SuccessResponse<>(userBusiness.login(bo));
    }

}
