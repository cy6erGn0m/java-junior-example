package com.levelp.example;

public enum SubjKind {
    REGION("R"),
    BUILDING("B");

    private final String dbValue;

    SubjKind(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }
}
