package com.example.toby.springbook.user.dao;



import com.example.toby.springbook.user.domain.User;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDao {
    private ConnectionMaker connectionMaker;
    private Connection c;
    private User user;
    private DataSource dataSource;

//    public UserDao(){//의존 관계 검색을 이용하는 UserDao 생성자
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        this.connectionMaker = context.getBean("connectionMaker",ConnectionMaker.class);
//    }


//    public UserDao(ConnectionMaker connectionMaker){
//        this.connectionMaker = connectionMaker;
//    }
//public static void main(String[] args) {
//    JUnitCore.main("com.example.toby.springbook.user.dao.UserDaoTest3");
//}

    //setDataSource는 고정이다

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void setConnectionMaker(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }
    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection c = dataSource.getConnection();
        //인터페이스에 정의된 메소드를 사용하므로 고객이 바뀌어도 메소드 이름은 항상 똑같다


        PreparedStatement ps = c.prepareStatement(
                "insert into users(id,name,password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException {

//        Connection c = connectionMaker.makeConnection();
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        User user = null;

        if(rs.next()) {

            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }
        rs.close();
        ps.close();
        c.close();

        if(user == null) throw new EmptyResultDataAccessException(1);

        return user;

    }

    public void deleteAll() throws SQLException{
        Connection c = null;

        PreparedStatement ps =null;

        try{ // 예외가 발생할 가능성이 있는 코드를 모두 try 블록으로 묶어준다.
            c= dataSource.getConnection();
            ps = c.prepareStatement("delete from users");
            ps.executeUpdate();
        }catch(SQLException e){ //예외가 발생했을 때 부가적인 작업을 해줄 수 있도록 catch블록을 둔다.
            throw e;
        }finally{
            if(ps != null){
                try{
                    ps.close();
                }catch(SQLException e){

                }
            }
            if(c != null){
                try{
                    c.close();
                }catch(SQLException e){

                }
            }
        }


        ps.close();
        c.close();
    }

    public int getCount() throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            c = dataSource.getConnection();

            ps = c.prepareStatement("select count(*) from users");

            rs= ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch(SQLException e){
            throw e;
        }finally{
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException e){

                }
            }
            if(ps != null){
                try{
                    ps.close();
                }catch(SQLException e){

                }
            }
            if(c != null){
                try{
                    c.close();
                }catch(SQLException e){

                }
            }
        }
    }

}



























