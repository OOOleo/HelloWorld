package com.leo.mybatis.mapper;

import com.leo.mybatis.bean.User;

import java.util.List;

public interface UserMapper {
    public  List<User> findList();

    public int addUser(User user);

}
