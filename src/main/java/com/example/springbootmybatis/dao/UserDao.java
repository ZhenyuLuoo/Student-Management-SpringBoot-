package com.example.springbootmybatis.dao;

import com.example.springbootmybatis.pojo.User;
import com.example.springbootmybatis.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository //deliver to spring container to manage
public interface UserDao {
    //search all users
    public List<User> listUser();

    //search based on id
    public User queryUserById(Integer id);

    //search based on username and show by differ pages
    public List<User> listUserByName(UserQuery userQuery);

    //Delete by id
    public int deleteUserById(Integer id);

    //update user
    public boolean updateUser(User user);

}
