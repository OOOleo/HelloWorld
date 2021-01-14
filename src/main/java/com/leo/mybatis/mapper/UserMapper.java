package com.leo.mybatis.mapper;

import com.leo.mybatis.bean.Column;
import com.leo.mybatis.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> findList();

    int addUser(User user);

    List<Column> getColumnInfo(@Param("dbName") String dbName, @Param("tableName") String tableName);

    User getById(int id);

    int updateUserById(User user);

    int deleteUser(int id);

    List<User> findUserByName(String username);

    List<User> findUserByName1(String username);

    /**
     * 按照时间的区间查
     * 多参数建议Map传递
     *
     * @param map
     */

    List<User> findUsersByBirthday(Map<String, Object> map);

    /**
     * 查询总数
     */
    int getTotalCount();

    /**
     * 查age的最大值和最小值
     */
    Map<String, Object> getUsers();

    //-------动态sql----------------------------------------------------------------------------------------------------------

    List<User> selectUserByNameAndAge(User user);

}
