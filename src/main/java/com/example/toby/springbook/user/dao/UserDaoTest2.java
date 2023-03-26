package com.example.toby.springbook.user.dao;

import com.example.toby.springbook.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

//클라이언트 역할
public class UserDaoTest2 { //관심사는 only test

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        ConnectionMaker connectionMaker = new DConnectionMaker();

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao3 = context.getBean("userDao",UserDao.class); //userDao라는 이름의 빈을 가져온다.
        UserDao dao4 = context.getBean("userDao",UserDao.class); //userDao라는 이름의 빈을 가져온다.

        System.out.println(dao3);
        System.out.println(dao4);

        System.out.println(dao3.equals(dao4));
//
    }
}
