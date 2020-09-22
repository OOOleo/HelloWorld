package com.leo.mybatis.mapper.impl;

import com.leo.mybatis.bean.User;
import com.leo.mybatis.mapper.UserMapper;
import com.leo.mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class UserMapperImpl implements UserMapper {
    @Override
    public List<User>  findList() {
        //配置文件路径
        SqlSession session = MybatisUtils.getSession();
        List<User> list=session.selectList("com.leo.mybatis.mapper.UserMapper.findList");
        MybatisUtils.closeSession(session);
        return list;
    }

    @Override
    public int addUser(User user) {
        SqlSession session = MybatisUtils.getSession();
        int count = session.insert("com.leo.mybatis.mapper.UserMapper.addUser", user);
        //session.commit();
        MybatisUtils.closeSession(session);
        return count;
    }
}
