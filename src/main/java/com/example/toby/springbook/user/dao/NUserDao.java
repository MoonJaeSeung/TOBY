package com.example.toby.springbook.user.dao;//package springbook.user.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class NUserDao extends UserDao{
//    public Connection getConnection() throws ClassNotFoundException, SQLException{
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection c = DriverManager.getConnection(
//                "jdbc:mysql://127.0.0.1:3307/spring","root","0000");
//        return c;
//    }
//
//
//}
