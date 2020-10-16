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

    /**
     * Таблица пользователей 'currency'
     */
    public static final String CURRENCY_ID = "id";
    public static final String CURRENCY_ISO = "iso";
    public static final String CURRENCY_NAME_RU = "nameRU";
    public static final String CURRENCY_NAME_EN = "nameEN";

    /**
     * Таблица пользователей 'rateNB'
     */
    public static final String RATENB_ID = "id";
    public static final String RATENB_CURRENCY_ID = "currencyId";
    public static final String RATENB_DATE = "date";
    public static final String RATENB_SUM = "sum";

    /**
     * Таблица пользователей 'rateCB'
     */
    public static final String RATECB_ID = "id";
    public static final String RATECB_COMING = "coming";
    public static final String RATECB_SPENDING = "spending";
    public static final String RATECB_TIMESTAMP = "timestamp";
    public static final String RATECB_SUM = "sum";
    public static final String RATECB_IS_BACK = "isBack";

    /**
     * Таблица пользователей 'duties'
     */
    public static final String DUTIES_ID = "id";
    public static final String DUTIES_USER_ID = "userId";
    public static final String DUTIES_TIMESTAMP = "timestamp";
    public static final String DUTIES_NUMBER = "number";
    public static final String DUTIES_IS_CLOSE = "isClose";

    /**
     * Картотека 'kassa'
     */
    public static final String KASSA_ID = "id";
    public static final String KASSA_CURRENCY_ID = "currencyId";
    public static final String KASSA_RESEIVED = "received";
    public static final String KASSA_COMING = "coming";
    public static final String KASSA_SPENDING = "spending";
    public static final String KASSA_TRANSMITTED = "transmitted";
    public static final String KASSA_BALANCE = "balance";
    public static final String KASSA_USER_ID = "userId";
    public static final String KASSA_DATE = "date";
    public static final String KASSA_DUTIES_ID = "dutiesId";


    private ColumnName() {
    }
}
