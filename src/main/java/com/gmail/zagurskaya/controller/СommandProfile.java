package com.gmail.zagurskaya.controller;

import com.gmail.zagurskaya.entity.Role;
import com.gmail.zagurskaya.entity.User;
import com.gmail.zagurskaya.service.RoleService;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.impl.RoleServiceImpl;
import com.gmail.zagurskaya.service.impl.UserServiceImpl;
import com.gmail.zagurskaya.util.DataUtil;

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
