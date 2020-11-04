package com.ldx.utils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author lidongxu
 * @date 2020/10/27
 * @Description
 */
public class DruidUtils {

    private DruidUtils() {}

    private static DruidDataSource druidDataSource = new DruidDataSource();

    static {
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test_mybatis");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
    }

    public static DruidDataSource getInstance() {
        return druidDataSource;
    }

}
