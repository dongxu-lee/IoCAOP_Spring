package com.ldx.service.impl;

import com.ldx.beans.factory.BeanFactory;
import com.ldx.dao.UserDao;
import com.ldx.pojo.User;
import com.ldx.service.UserService;
import com.ldx.utils.TransactionManager;

public class UserServiceImpl implements UserService {

    //private UserDao userDao = (UserDao) BeanFactory.getBean("userDao");

    private UserDao userDao;

    //set方法或构造函数实现bean初始化

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(int id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public int updateUserById(User user) throws Exception {
        int i = 0;
//        try {
//            //开启事务
//            TransactionManager.getInstance().beginTransaction();
            i = userDao.updateById(user);
            //int x = 1/0;
            User user1 = new User();
            user1.setId(user.getId());
            user1.setUsername("error");
            userDao.updateById(user1);
//            TransactionManager.getInstance().commit();
//        }catch (Exception e) {
//            e.printStackTrace();
//            TransactionManager.getInstance().rollback();
//            throw e;
//        }
        return i;
    }
}
