package com.leo.test;

import java.sql.*;
import java.util.*;

public class JDBCUtils {




    public static JDBCSourceModel getAllSourceInfo(Connection conn) {


        JDBCSourceModel sourceModel= new JDBCSourceModel();
        try{
            DatabaseMetaData metaData = conn.getMetaData();
            String schema = "Oracle".equals(metaData.getDatabaseProductName()) ? getSchema(conn) : null;
            ResultSet tables = metaData.getTables(null, schema, "%", new String[]{"TABLE"});
            Map<String, JDBCTableModel> tableModelMap = new LinkedHashMap<>();
            sourceModel.setTableModelMap(tableModelMap);
            while (tables.next()) {
                JDBCTableModel tableModel = new JDBCTableModel();
                Map<String, JDBCColumnModel> columnModelMap = new LinkedHashMap<>();
                String tableName = tables.getString("TABLE_NAME");
                tableModel.setTableName(tableName);
                tableModel.setSourceName(sourceModel.getSourceName());
                tableModel.setColumnModelMap(columnModelMap);
                ResultSet columns = metaData.getColumns(conn.getCatalog(), null, tableName, null);
                ResultSet keys = metaData.getPrimaryKeys(conn.getCatalog(), null, tableName);


                HashSet<String> primaryKeySet = new HashSet<>();
                while (keys.next()) {
                    primaryKeySet.add(keys.getString("COLUMN_NAME"));
                }
                while (columns.next()) {
                    JDBCColumnModel colModel = new JDBCColumnModel();
                    String colName = columns.getString("COLUMN_NAME");
                    colModel.setColName(colName);
                    if (primaryKeySet.contains(colName)) {
                        colModel.setIsPrimaryKey(true);
                    }else{
                        colModel.setIsPrimaryKey(false);
                    }
                    colModel.setTableName(tableName);
                    colModel.setDataType(columns.getInt("DATA_TYPE"));
                    colModel.setDataLength(columns.getInt("COLUMN_SIZE"));
                    columnModelMap.put(colName,colModel);
                }
                tableModelMap.put(tableName,tableModel);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return sourceModel;
    }




    /**
     * 选取数据源后  拉取表名列表
     * @param conn
     * @return List<String>表名列表
     */


    public static List<String> getTableListOfSource(Connection conn){
        List<String> tableList = new ArrayList<>();
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            String schema = "Oracle".equals(metaData.getDatabaseProductName()) ? getSchema(conn) : null;
            ResultSet tables = metaData.getTables(null, schema, "%", new String[]{"TABLE"});
            while (tables.next()) {
                tableList.add(tables.getString("TABLE_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableList;
    }




    /**
     * 根据数据源和表名拉取字段列表
     * @param conn
     * @param tableName
     * @return
     */


    public static List<JDBCColumnModel> getColumnListOfTable(Connection conn, String tableName) {
        List<JDBCColumnModel> columnList = new ArrayList<>();
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            String schema = "Oracle".equals(metaData.getDatabaseProductName()) ? getSchema(conn) : null;
            ResultSet columns = metaData.getColumns(conn.getCatalog(), schema, tableName, null);
            ResultSet keys = metaData.getPrimaryKeys(conn.getCatalog(), schema, tableName);


            //获取主键列表
            HashSet<String> primaryKeySet = new HashSet<>();
            while (keys.next()) {
                primaryKeySet.add(keys.getString("COLUMN_NAME"));
            }
            while (columns.next()) {
                JDBCColumnModel colModel = new JDBCColumnModel();
                String colName = columns.getString("COLUMN_NAME");
                colModel.setColName(colName);
                //判断字段是不是主键
                if (primaryKeySet.contains(colName)) {
                    colModel.setIsPrimaryKey(true);
                }else{
                    colModel.setIsPrimaryKey(false);
                }
                colModel.setTableName(tableName);
                colModel.setDataType(columns.getInt("DATA_TYPE"));
                colModel.setDataTypeName(columns.getString("TYPE_NAME"));
                colModel.setDataLength(columns.getInt("COLUMN_SIZE"));
                columnList.add(colModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnList;
    }


    public static boolean isSqlWorks(Connection conn, String sql) {
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeQuery();
        }catch (SQLException e) {
            return false;
        }
        return true;
    }


    public static Connection getConnection(JDBCConnectionModel connModel) {
        String server = connModel.getServer();
        Integer port = connModel.getPort();
        String dbName = connModel.getDbName();
        String driver = null;
        Connection conn = null;
        StringBuilder urlSB = new StringBuilder("jdbc:");
        switch (connModel.getSourceType()) {
            case MYSQL:
                driver = "com.mysql.cj.jdbc.Driver";
                urlSB.append("mysql://").append(server).append(":").append(port).append("/").append(dbName);
                break;
            case ORACLE:
                driver = "oracle.jdbc.driver.OracleDriver";
                urlSB.append("oracle:thin:@").append(server).append(":").append(port).append(":").append(dbName);
                break;
            case SQLSERVER:
                driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
                urlSB.append("sqlserver://").append(server).append(":").append(port).append(";DatabaseName=").append(dbName);
                break;
            case HIVE:
                driver = "org.apache.hadoop.hive.jdbc.HiveDriver";
                urlSB.append("hive://").append(server).append(":").append(port).append("/").append(dbName);
                break;
            default:
                break;
        }


        Properties props = new Properties();
        props.setProperty("user", connModel.getUserName());
        props.setProperty("password", connModel.getPassword());
        //为防止加载无关表
        props.setProperty("nullCatalogMeansCurrent", "true");
        props.setProperty("serverTimezone","UTC");
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(urlSB.toString(), props);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return conn;
    }
    private static String getSchema(Connection conn) throws Exception {
        String schema;
        schema = conn.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase();
    }


}
