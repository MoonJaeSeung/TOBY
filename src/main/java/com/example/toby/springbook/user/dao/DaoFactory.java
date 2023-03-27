package com.example.toby.springbook.user.dao;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
public class DaoFactory {

    @Bean //오브젝트 생성을 담당하는 ioC용 메소드라는 표시
    public UserDao userDao(){
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());
        return userDao;
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//        return userDao;
//        //UserDao타입의 오브젝트를 어떻게 만들고 어떻게 준비시킬지 결정
    }
//    public AccountDao accountDao(){
//        return new AccountDao(connectionMaker());
//    }
//
//    public MessageDao messageDao(){
//        return new MessageDao(connectionMaker());
//    }

    @Bean
    public ConnectionMaker connectionMaker(){ //분리해서 중복을 제거함
        return new DConnectionMaker();
    }
}
