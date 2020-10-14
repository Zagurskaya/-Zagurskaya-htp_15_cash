package com.zagurskaya.cash.entity;

/**
 * Пользователь со свойствами <b>id</b>, <b>login</b>, <b>password</b>, <b>fullName</b> и <b>role</b>.
 */
public class User {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Логин
     */
    private String login;
    /**
     * Пароль
     */
    private String password;
    /**
     * ФИО
     */
    private String fullName;
    /**
     * Роль
     */
    private RoleEnum role;

    /**
     * Получение значения поля {@link User#id}
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Получение значения поля {@link User#login}
     *
     * @return логин
     */
    public String getLogin() {
        return login;
    }

    /**
     * Получение значения поля {@link User#password}
     *
     * @return пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * Получение значения поля {@link User#fullName}
     *
     * @return ФИО
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Получение значения поля {@link User#role}
     *
     * @return роль
     */
    public RoleEnum getRole() {
        return role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{")
                .append("id=")
                .append(id)
                .append(", login=")
                .append(login)
                .append(", password=")
                .append(password)
                .append(", fullName=")
                .append(fullName)
                .append(", role=")
                .append(role)
                .append("}");
        return sb.toString();
    }

    /**
     * Конструирование пользователя.
     */
    public static class Builder {
        private User newUser;

        /**
         * Конструктор
         */
        public Builder() {
            newUser = new User();
        }

        /**
         * Определение идентификатора {@link User#id}
         *
         * @param id - идентификатор
         * @return Builder
         */
        public Builder addId(Long id) {
            newUser.id = id;
            return this;
        }

        /**
         * Определение идентификатора {@link User#login}
         *
         * @param login - логин
         * @return Builder
         */
        public Builder addLogin(String login) {
            newUser.login = login;
            return this;
        }

        /**
         * Определение идентификатора {@link User#password}
         *
         * @param password - пароль
         * @return Builder
         */
        public Builder addPassword(String password) {
            newUser.password = password;
            return this;
        }

        /**
         * Определение идентификатора {@link User#fullName}
         *
         * @param fullName - ФИО
         * @return Builder
         */
        public Builder addFullName(String fullName) {
            newUser.fullName = fullName;
            return this;
        }

        /**
         * Определение идентификатора {@link User#role}
         *
         * @param role - роль
         * @return Builder
         */
        public Builder addRole(RoleEnum role) {
            newUser.role = role;
            return this;
        }

        /**
         * Определение идентификатора {@link User#role}
         *
         * @param role - идентификатор
         * @return Builder
         */
        public Builder addRole(String role) {
            newUser.role = RoleEnum.valueOf(role.toUpperCase());
            return this;
        }

        /**
         * Возвращает построенного пользователя
         *
         * @return пользователь
         */
        public User build() {
            return newUser;
        }
    }
}
