package com.example.toby.springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{


    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
                Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/user","root","0000");
        return c;
    }
}
