package com.ldx.controller;

import com.ldx.beans.factory.BeanFactory;
import com.ldx.pojo.User;
import com.ldx.proxy.ProxyFactory;
import com.ldx.service.UserService;

public class UserController {

    //private UserService userService = (UserService) BeanFactory.getBean("userService");


    //从工厂获取委托对象（委托对象是增强了事务控制的功能）

    //首先从BeanFactory获取到proxyFactory代理工厂的实例化对象
    private ProxyFactory proxyFactory = (ProxyFactory) BeanFactory.getBean("proxyFactory");
    private UserService userService = (UserService) proxyFactory.getJdkProxy(BeanFactory.getBean("userService"));

    public static void main(String[] args) throws Exception {
        //System.out.println(new UserController().userService.getUserById(3));
        User user = new User();
        user.setId(1);
        user.setUsername("Jimmy");
        System.out.println(new UserController().userService.updateUserById(user));
    }

}
