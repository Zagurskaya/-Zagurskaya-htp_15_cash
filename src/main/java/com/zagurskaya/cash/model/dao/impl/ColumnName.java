package com.zagurskaya.cash.model.dao.impl;

public class ColumnName {
    // format -> TABLE_NAME

    // table 'user'
    public static final String USER_ID = "id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE_ID = "roleId";

    // table 'role'
    public static final String ROLE_ID = "id";
    public static final String ROLE_NAME = "name";

    private ColumnName() {
    }
}
