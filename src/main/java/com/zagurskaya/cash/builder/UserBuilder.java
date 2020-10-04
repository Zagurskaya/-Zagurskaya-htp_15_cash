package com.zagurskaya.cash.builder;

import com.zagurskaya.cash.entity.RoleEnum;
import com.zagurskaya.cash.entity.User;

public class UserBuilder {
    private User newUser;

    public UserBuilder() {
        newUser = new User();
    }

    public UserBuilder addId(Long id) {
        newUser.setId(id);
        return this;
    }

    public UserBuilder addLogin(String login) {
        newUser.setLogin(login);
        return this;
    }

    public UserBuilder addPassword(String password) {
        newUser.setPassword(password);
        return this;
    }

    public UserBuilder addFullName(String fullName) {
        newUser.setFullName(fullName);
        return this;
    }

    public UserBuilder addRole(RoleEnum role) {
        newUser.setRole(role);
        return this;
    }

    public User build() {
        return newUser;
    }

}
