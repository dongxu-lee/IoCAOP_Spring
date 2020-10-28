package com.ldx.dao.impl;

import com.ldx.dao.UserDao;
import com.ldx.pojo.User;
import com.ldx.utils.ConnectionUtils;
import com.ldx.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Override
    public User findById(int id) throws Exception {
        //Connection con = DruidUtils.getInstance().getConnection();
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "select * from user where id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User u = new User();
        u.setId(id);
        while (resultSet.next()) {
            u.setUsername(resultSet.getString("username"));
        }
        resultSet.close();
        preparedStatement.close();
        //con.close();
        return u;

    }

    @Override
    public int updateById(User user) throws Exception {
        //Connection con = DruidUtils.getInstance().getConnection();
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "update user set username = ? where id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setInt(2, user.getId());
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        //con.close();
        return i;

    }
}
