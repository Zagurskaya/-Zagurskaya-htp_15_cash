package com.zagurskaya.cash.entity;

/**
 * User role.
 */
public enum RoleType {
    /**
     * Administrator
     */
    ADMIN("admin"),
    /**
     * Cashier
     */
    KASSIR("kassir"),
    /**
     * Controller
     */
    CONTROLLER("controller");

    private String value;

    /**
     * Constructor
     *
     * @param value - role value
     */
    RoleType(String value) {
        this.value = value;
    }

    /**
     * Get field value {@link RoleType#value}
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value {@RoleEnum#value}
     *
     * @param value - role value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
