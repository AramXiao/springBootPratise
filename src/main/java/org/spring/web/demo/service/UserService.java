package org.spring.web.demo.service;

import org.spring.web.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
@CacheConfig(cacheNames = "c1")
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public int addUser(User user){
        return jdbcTemplate.update("insert into user(username,address)values(?,?)", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getAddress());
            }
        });

    }

    public int addUser2(User user){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update =  jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement("insert into user(username,address)values(?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,user.getUsername());
                ps.setString(2,user.getAddress());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
        return update;
    }

    @Cacheable
    public List<User> getAllUers(){
        return jdbcTemplate.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                String username = rs.getString("username");
                String address = rs.getString("address");
                int id = rs.getInt("id");
                User user = new User();
                user.setId(id);
                user.setAddress(address);
                user.setUsername(username);
                return user;
            }
        });
    }

    @CachePut
    public int updateUser(User user){
        return jdbcTemplate.update("update user set username=?,address=? where id=?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getAddress());
                ps.setInt(3, user.getId());
            }
        });

    }

    @Cacheable(key="#id",unless = "#result == null")
    public User getUserById(Integer id){
        System.out.println("Entry getUserById");
        final String sql = "select * from user where id="+id;
        User user = null;
        try{
            user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                String username = rs.getString("username");
                String address = rs.getString("address");
                int id = rs.getInt("id");
                User user = new User();
                user.setId(id);
                user.setAddress(address);
                user.setUsername(username);
                return user;
            }
        });
        }catch (EmptyResultDataAccessException e){

        }
        return user;
    }

    @CacheEvict
    public int deleteUserById(Integer id){
        final String sql = "delete from user where id=?";
        int result = jdbcTemplate.update(sql, id);
        return result;
    }

}
