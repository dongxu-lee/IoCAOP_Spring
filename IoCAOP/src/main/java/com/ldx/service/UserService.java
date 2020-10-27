package com.ldx.service;

import com.ldx.pojo.User;

public interface UserService {
    User getUserById(int id) throws Exception;

    int updateUserById(User user) throws Exception;
}
