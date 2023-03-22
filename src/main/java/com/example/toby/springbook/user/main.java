package com.example.toby.springbook.user;//package springbook.user;
//
//import springbook.user.dao.UserDao;
//import springbook.user.domain.User;
//
//import java.sql.SQLException;
//
//public class main {
//
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        UserDao dao = new UserDao();
//
//        String classpath = System.getProperty("java.class.path");
//        System.out.println("클래스 패스 : " + classpath);
//
//        User user = new User();
//        user.setId("whiteship2");
//        user.setName("백기선2");
//        user.setPassword("married");
//
//        dao.add(user);
//
//        System.out.println(user.getId() + "등록 성공");
//
//        User user2 = dao.get(user.getId());
//        System.out.println(user2.getName());
//
//        System.out.println(user2.getPassword());
//
//        System.out.println(user2.getId() + "조회 성공");
//
//    }
//}
//
//
//
