package com.leo.other;



public enum DataType {
    STRING("String"),
    INT("int"),
    FLOAT("float"),
    DOUBLE("double");



    private String type;


    DataType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
