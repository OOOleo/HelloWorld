package com.leo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JDBCTableModel {

    private String sourceName;

    private String tableName;

    private String showName;

    private Map<String,JDBCColumnModel> columnModelMap;
}