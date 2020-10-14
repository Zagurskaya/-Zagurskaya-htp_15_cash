package com.zagurskaya.cash.entity;

/**
 * Роли пользователей.
 */
public enum RoleEnum {
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
    RoleEnum(String value) {
        this.value = value;
    }

    /**
     * Получение значения {@link RoleEnum#value}
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
