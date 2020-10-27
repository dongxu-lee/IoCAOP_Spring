package com.ldx.dao.impl;

import com.ldx.dao.UserDao;
import com.ldx.pojo.User;

public class UserDaoImpl implements UserDao {
    @Override
    public User findById(int id) {
        if(id == 1) {
            return new User(1, "james");
        }
        return new User(2, "londo");
    }
}
