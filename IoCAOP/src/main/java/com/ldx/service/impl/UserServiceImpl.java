package com.ldx.service.impl;

import com.ldx.beans.factory.BeanFactory;
import com.ldx.dao.UserDao;
import com.ldx.pojo.User;
import com.ldx.service.UserService;

public class UserServiceImpl implements UserService {

    //private UserDao userDao = (UserDao) BeanFactory.getBean("userDao");

    private UserDao userDao;

    //set方法或构造函数实现bean初始化

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(int id) {
        return userDao.findById(id);
    }
}
