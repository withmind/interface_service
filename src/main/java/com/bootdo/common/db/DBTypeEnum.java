package com.bootdo.common.db;

/**
 * @Author DGD
 * @date 2018/2/7.
 */
public enum DBTypeEnum {
    master("master"), db2("db2");
    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
