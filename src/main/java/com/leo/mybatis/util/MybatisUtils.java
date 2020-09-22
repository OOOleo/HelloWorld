package com.leo.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {

    public static SqlSession getSession() {
        InputStream inputStream = null;
        String resource = "mybatis-config.xml";
        try {
            //加载配置文件得到输入流
            inputStream = Resources.getResourceAsStream(resource);
            //获取session工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过session工厂获取到一个session 这个session表示Mybatis框架和数据库的会话信息
            //获取到这个session就表示Mybatis连接上数据库了  类似JDBC中的connection对象
            //openSession里true  设置自动提交事务
            SqlSession session = sqlSessionFactory.openSession(true);
            return session;

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void closeSession(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }

}
