package com.zagurskaya.cash.entity;

public enum RoleEnum {

    ADMIN ("Admin"),
    KASSIR ("Kassir");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
