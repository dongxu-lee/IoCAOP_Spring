package com.ldx.controller;

import com.ldx.beans.factory.BeanFactory;
import com.ldx.service.UserService;

public class UserController {

    private UserService userService = (UserService) BeanFactory.getBean("userService");

    public static void main(String[] args) {
        System.out.println(new UserController().userService.getUserById(0));
    }

}
