package com.leo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JDBCColumnModel {

    private String colName;

    private String showName;

    private String tableName;

    private Boolean isPrimaryKey;

    private int dataType;

    private String dataTypeName;

    private int dataLength;
}
