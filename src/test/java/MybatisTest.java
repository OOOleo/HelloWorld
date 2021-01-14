import com.leo.mybatis.bean.Column;
import com.leo.mybatis.bean.User;
import com.leo.mybatis.mapper.UserMapper;
import com.leo.mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    SqlSession sqlSession = null;
    UserMapper um = null;

    //每次调用测试方法之前  自动调用init()方法
    @Before
    public void init(){
        //mybatis在底层使用动态代理（反射）自动生成Mapper实现类  不需要人工写实现类
         sqlSession = MybatisUtils.getSession();
        //session um就是Mapper的实现类
         um = sqlSession.getMapper(UserMapper.class);
    }


    @Test
    public void testQuery() {
        List<User> list = um.findList();
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * mybatis增删改需要手动提交事务
     * 新增成功后 获得主键id
     * useGeneratedKeys  propertyKey
     */
    @Test
    public void testAdd() {
        User user = new User(0L, "dddd", 32, "fdsgfsdsd@163.com");
        int res = um.addUser(user);
        System.out.println(user.getId());
        System.out.println(res > 0 ? "Success" : "Failed");
    }

    /**
     * 获取列信息
     */
    @Test
    public void testGetColumn() {
        List<Column> list = um.getColumnInfo("Test", "user");
//        for (Column col : list) {
//            System.out.println(col);
//        }
        list.forEach(System.out::println);
    }

    /**
     * 根据id查询
     */
    @Test
    public void testGetById() {
        User user = um.getById(3);
        System.out.println(user);
    }


    @Test
    public void testUpdate() {
        User user = um.getById(3);
        user.setAge(30);
        int count = um.updateUserById(user);
        System.out.println(count>0?"更新成功":"更新失败");

    }

    @Test
    public void testDelete() {
        int count = um.deleteUser(12);
        System.out.println(count>0?"删除成功":"删除失败");
    }

    @Test
    public void testLike() {
        String keys = "le";
        List<User> list = um.findUserByName("%"+keys+"%");
        list.forEach(System.out::println);
    }

    @Test
    public void testLike1() {
        List<User> list = um.findUserByName1("le");
        list.forEach(System.out::println);
    }

    @Test
    public void testMap1() {
        Map<String, Object> map = new HashMap<>();
        map.put("low_age", 25);
        map.put("high_age", 30);
        List<User> list = um.findUsersByBirthday(map);
        list.forEach(System.out::println);
    }

    @Test
    public void testTotalCount() {
        int count = um.getTotalCount();
        System.out.println(count);
    }

    @Test
    public void testGetUsers() {
        Map<String, Object> map = um.getUsers();
        System.out.println(map);
    }

    /** 动态sql*/
    @Test
    public void test1() {
        User u = new User();
        u.setName("%e%");
        //u.setAge(25);
        List<User> list=um.selectUserByNameAndAge(u);
        list.forEach(System.out::println);

    }

    @After
    public void destroy() {
        sqlSession.close();
    }
}
