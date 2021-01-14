package com.leo.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JDBCConnectionModel {
    private String server;
    private Integer port;
    private SqlDataSourceEnum sourceType;
    private String dbName;
    private String userName;
    private String password;
}