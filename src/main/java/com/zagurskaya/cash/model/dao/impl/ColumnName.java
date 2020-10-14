package com.zagurskaya.cash.model.dao.impl;

/**
 * Наименование в БД колонок в таблице
 */
public class ColumnName {
    // format -> TABLE_NAME

    /**
     * Таблица пользователей 'user'
     */
    public static final String USER_ID = "id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FULL_NAME = "fullname";
    public static final String USER_ROLE = "role";


    private ColumnName() {
    }
}
