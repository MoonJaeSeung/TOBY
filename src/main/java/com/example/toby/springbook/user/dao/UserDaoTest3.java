package com.example.toby.springbook.user.dao;

import com.example.toby.springbook.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationContext.xml")
public class UserDaoTest3 {

    private User user1;
    private User user2;
    private User user3;

    @Autowired
    private ApplicationContext context;

    @Autowired
    UserDao dao;

    @Autowired
    SimpleDriverDataSource dataSource;

    @Before
    public void setUp(){ // 픽스처의 모음
//        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        this.dao = context.getBean("userDao", UserDao.class);

         user1 = new User("JS","문재승","no1");
         user2 = new User("YH","김윤한","no2");
         user3 = new User("SJ","황석준","no3");

        System.out.println(this.context);
        System.out.println(this);

//        DataSource dataSource = new SingleConnectionDataSource(
//                "jdbc:mysql://127.0.0.1:3306/testdb","root","0000",true
//        );
        dao.setDataSource(dataSource);

    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {



        dao.deleteAll();
        assertThat(dao.getCount(),is(0));



        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(),is(2));


        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(),is(user1.getName()));
        assertThat(userget1.getPassword(),is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(),is(user2.getName()));
        assertThat(userget2.getPassword(),is(user2.getPassword()));

    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {




        dao.deleteAll();
        assertThat(dao.getCount(),is(0));

        dao.add(user1);
        assertThat(dao.getCount(),is(1));

        dao.add(user2);
        assertThat(dao.getCount(),is(2));

        dao.add(user3);
        assertThat(dao.getCount(),is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException,ClassNotFoundException{

        dao.deleteAll();
        assertThat(dao.getCount(),is(0));

        dao.get("unknown_id");
    }
}
