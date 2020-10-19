package com.zagurskaya.cash.entity;

/**
 * Роли пользователей.
 */
public enum RoleType {
    /**
     * Администратор
     */
    ADMIN("admin"),
    /**
     * Кассир
     */
    KASSIR("kassir"),
    /**
     * Контролер
     */
    CONTROLLER("controller");

    private String value;

    /**
     * Конструктор - создание нового объекта с определенным значением
     *
     * @param value - значение
     */
    RoleType(String value) {
        this.value = value;
    }

    /**
     * Получение значения {@link RoleType#value}
     *
     * @return значение
     */
    public String getValue() {
        return value;
    }

    /**
     * Определение значения {@RoleEnum#value}
     *
     * @param value - значения
     */
    public void setValue(String value) {
        this.value = value;
    }
}
