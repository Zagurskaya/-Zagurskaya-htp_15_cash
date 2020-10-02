package com.zagurskaya.cash.entity;

public class User implements Entity {
    private Long id;
    private String login;
    private String password;
    private RoleEnum role;

    public User() {
    }

    public User(Long id, String login, String password, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
//        this.role = role;
        this.role = RoleEnum.valueOf(role);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
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
                .append(", role=")
                .append(role)
                .append("}");
        return sb.toString();
    }
}
