package org.spring.web.demo.dao.mapper;

import org.apache.ibatis.annotations.*;
import org.spring.web.demo.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Results(
            {
                    @Result(property = "id", column = "id"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "address", column = "address")
            }
    )
    @Select("select * from user")
    List<User> getAllUser();

    @Select("select id,username, address from user where id=#{id}")
    User getUserById(Long id);

    @Select("select * from user where username like concat('%',#{name},'%')")
    List<User> getUsersByName(String name);

    @Insert({"insert into user(username,address) values(#{username},#{address})"})
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
    Integer addUser(User user);

    @Update("update user set username=#{username},address=#{address} where id=#{id}")
    Integer updateUserById(User user);

    @Delete("delete from user where id=#{id}")
    Integer deleteUserById(Integer id);

}
