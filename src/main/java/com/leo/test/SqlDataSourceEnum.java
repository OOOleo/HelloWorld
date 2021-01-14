package com.leo.test;



public enum SqlDataSourceEnum {
    /**
     * mysql
     */
    MYSQL("mysql","com.mysql.cj.jdbc.Driver"),
    /**
     * sqlserver
     */
    SQLSERVER("sqlserver","com.microsoft.jdbc.sqlserver.SQLServerDriver"),
    /**
     * oracle
     */
    ORACLE("oracle","oracle.jdbc.driver.OracleDriver"),
    /**
     * hive
     */
    HIVE("hive","org.apache.hadoop.hive.jdbc.HiveDriver");



    private String driver;

    private String name;

    public String getDriver() {
        return driver;
    }

    public String getName() {
        return name;
    }

    SqlDataSourceEnum(String name, String driver){
        this.driver=driver;
        this.name=name;
    }


}