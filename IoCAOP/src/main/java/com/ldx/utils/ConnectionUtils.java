package com.ldx.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author lidongxu
 * @date 2020/10/27
 * @Description 当前线程绑定数据库连接，保证每个线程获取到的都是同一个连接
 */
public class ConnectionUtils {

    private ConnectionUtils() {}

    private static ConnectionUtils connectionUtils = new ConnectionUtils();
    //保证每个dao实现类取到的ConnectionUtils都是单例的，
    // 进而保证取到的threadLocal都是同一个，其中的connection也就是同一个
    public static ConnectionUtils getInstance() {
        return connectionUtils;
    }

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>(); //存储当前线程的连接

    /**
     * 从当前线程获取连接
     * @return
     * @throws SQLException
     */
    public Connection getCurrentThreadConn() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection == null) {
            connection = DruidUtils.getInstance().getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }


}
