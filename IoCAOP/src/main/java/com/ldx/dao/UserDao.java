package com.ldx.dao;

import com.ldx.pojo.User;

public interface UserDao {
    User findById(int id);
}
