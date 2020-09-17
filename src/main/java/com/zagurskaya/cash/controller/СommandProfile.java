package com.zagurskaya.cash.controller;

import com.zagurskaya.cash.entity.Role;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.service.RoleService;
import com.zagurskaya.cash.service.UserService;
import com.zagurskaya.cash.service.impl.RoleServiceImpl;
import com.zagurskaya.cash.service.impl.UserServiceImpl;
import com.zagurskaya.cash.util.DataUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class СommandProfile implements Сommand {
    private RoleService roleService = new RoleServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public Action execute(HttpServletRequest request) throws Exception {
        User user = DataUtil.findUser(request);
        if (user != null) {
//            List<Role> roles = new RoleDaoImpl().getAll();
            List<Role> roles = roleService.getAll();
            request.setAttribute("roles", roles);

//            List<User> users = new UserDaoImpl().getAll();
            List<User> users = userService.getAll();
            request.setAttribute("users", users);

            return Action.PROFILE;
        }
        return Action.LOGIN;
    }
}
