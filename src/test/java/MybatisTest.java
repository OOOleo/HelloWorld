import com.leo.mybatis.bean.User;
import com.leo.mybatis.mapper.UserMapper;
import com.leo.mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MybatisTest {
    //mybatis在底层使用动态代理（反射）自动生成Mapper实现类  不需要人工写实现类
    SqlSession sqlSession = MybatisUtils.getSession();

    //session方法获取实现类   um就是Mapper的实现类
    UserMapper um = sqlSession.getMapper(UserMapper.class);

    @Test
    public void testQuery() {
        List<User> list = um.findList();
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * mybatis增删改需要手动提交事务
     */
    @Test
    public void testAdd() {
        User user = new User(0L, "abc", 26, "l123456@163.com");
        int res = um.addUser(user);
        System.out.println(res > 0 ? "Success" : "Failed");
    }
}
