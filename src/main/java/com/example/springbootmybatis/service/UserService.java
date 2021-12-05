package com.example.springbootmybatis.service;

import com.example.springbootmybatis.pojo.User;
import com.example.springbootmybatis.pojo.query.UserQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    //search all users
    public List<User> listUser();

    //search based on username and show by differ pages
    public PageInfo<User> listUserByName(UserQuery userQuery);

    //Delete by id
    public boolean deleteUserById(Integer id);
}
