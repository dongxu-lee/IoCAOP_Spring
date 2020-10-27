package com.ldx.controller;

import com.ldx.beans.factory.BeanFactory;
import com.ldx.pojo.User;
import com.ldx.service.UserService;

public class UserController {

    private UserService userService = (UserService) BeanFactory.getBean("userService");

    public static void main(String[] args) throws Exception {
        //System.out.println(new UserController().userService.getUserById(3));
        User user = new User();
        user.setId(1);
        user.setUsername("tom");
        System.out.println(new UserController().userService.updateUserById(user));
    }

}
