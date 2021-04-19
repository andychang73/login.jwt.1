package com.abstractionizer.login.jwt.login.services;

import com.abstractionizer.login.jwt.db.rmdb.entities.User;

public interface UserService {

    User create(User user);

    User getUserByUsername(String username);
}
