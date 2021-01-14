import com.leo.test.JDBCColumnModel;
import com.leo.test.JDBCConnectionModel;
import com.leo.test.JDBCUtils;
import com.leo.test.SqlDataSourceEnum;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCTest {
    @Test
    public void testGetTableList() throws SQLException, ClassNotFoundException {
        JDBCConnectionModel connectionModel = new JDBCConnectionModel("192.168.18.85", 1521, SqlDataSourceEnum.ORACLE, "ORCL", "SUPOS", "supos1304");
        Connection conn = JDBCUtils.getConnection(connectionModel);
        String name=conn.getMetaData().getDatabaseProductName();
        List<String> list = JDBCUtils.getTableListOfSource(conn);
        list.forEach(System.out::println);
        conn.close();
    }

    /**
     * 根据连接信息和表名获取表的字段列表
     */
    @Test
    public void testGetColumnList() throws SQLException, ClassNotFoundException { JDBCConnectionModel connectionModel = new JDBCConnectionModel("192.168.12.43", 3306, SqlDataSourceEnum.MYSQL, "indicator", "root", "rootkit");
        JDBCConnectionModel connectionModel1 = new JDBCConnectionModel("192.168.18.85", 1521, SqlDataSourceEnum.ORACLE, "ORCL", "SUPOS", "supos1304");
        Connection conn = JDBCUtils.getConnection(connectionModel1);
        List<JDBCColumnModel> list = JDBCUtils.getColumnListOfTable(conn,"BASE_COMPANYSTAFF");
        list.forEach(System.out::println);
        conn.close();
    }
}
