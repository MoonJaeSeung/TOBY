//package com.example.toby.springbook.user.dao;
//
//import com.example.toby.springbook.user.domain.User;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import java.sql.SQLException;
//
//public class UserDaoConnectionCountingTest {
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(CountingDaoFactory.class);
//        UserDao dao = context.getBean("userDao",UserDao.class);
//
//        //DAO 사용 코드
//        User user = new User();
//        user.setId("whiteship2");
//        user.setName("백기선2");
//        user.setPassword("married");
//
//        dao.add(user);
//
//        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
//        System.out.println("Connection counter : " + ccm.getCounter());
//    }
//}
