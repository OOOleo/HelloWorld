package com.leo.druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.util.JdbcConstants;

public class Test {
    public static void main(String[] args) {
        String sql = "select avg(logout_time - login_time)  from log_model where eduction='本科' and level=1 group by dept_id";
        String result = SQLUtils.format(sql, JdbcConstants.MYSQL);
        System.out.println(result);
    }


}
