package com.example.toby.springbook.user.dao;

import com.example.toby.springbook.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


import java.sql.SQLException;

//클라이언트 역할
public class UserDaoTest { //관심사는 only test

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        ConnectionMaker connectionMaker = new DConnectionMaker();

        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao",UserDao.class); //userDao라는 이름의 빈을 가져온다.


//        String classpath = System.getProperty("java.class.path");
//        System.out.println("클래스 패스 : " + classpath);

        User user = new User();
        user.setId("whiteship2");
        user.setName("백기선2");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = dao.get(user.getId());

        if(!user.getName().equals(user2.getName())){
            System.out.println("테스트 실패(name)");
        }
        else if(!user.getPassword().equals(user2.getPassword())){
            System.out.println("테스트 실패(Password)");
        }
        else{
            System.out.println("조회 테스트 성공");
        }

//        System.out.println(user2.getName());
//        System.out.println(user2.getPassword());
//
//        System.out.println(user2.getId() + "조회 성공");
        //ㅇh

    }
}
