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
    private JdbcContext jdbcContext;

//    public void setJdbcContext(JdbcContext jdbcContext){
//        this.jdbcContext = jdbcContext;
//    }

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
        this.jdbcContext = new JdbcContext();

        this.jdbcContext.setDataSource(dataSource);

        this.dataSource = dataSource;

    }

    public void setConnectionMaker(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

//    abstract protected PreparedStatement makeStatement(Connection c) throws SQLException;
    public void add(final User user) throws SQLException {
        this.jdbcContext.workWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        PreparedStatement ps = c.prepareStatement(
                                "insert into users(id,name,password) values(?,?,?)");
                        ps.setString(1, user.getId());
                        ps.setString(2, user.getName());
                        ps.setString(3, user.getPassword());
                        return ps;
                    }
                }
                );
    }


    public void deleteAll() throws SQLException{    //클라이언트 책임 담당
        this.jdbcContext.workWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        return c.prepareStatement("delete from users");
                    }
                }
        );
    }

    public User get(String id) throws  SQLException {

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
//ft



























