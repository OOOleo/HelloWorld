package com.leo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JDBCSourceModel {


    private String sourceName;


    private String showName;


    private String connectId;


    private Integer sourceType;


    private Map<String,JDBCTableModel> tableModelMap;
}