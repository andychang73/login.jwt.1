package com.abstractionizer.login.jwt.login.businesses;

import com.abstractionizer.login.jwt.db.rmdb.entities.User;
import com.abstractionizer.login.jwt.models.bo.CreateUserBo;
import com.abstractionizer.login.jwt.models.bo.LoginBo;
import com.abstractionizer.login.jwt.models.dto.UserInfo;

public interface UserBusiness {

    User create(CreateUserBo bo);

    UserInfo login(LoginBo bo);
}
