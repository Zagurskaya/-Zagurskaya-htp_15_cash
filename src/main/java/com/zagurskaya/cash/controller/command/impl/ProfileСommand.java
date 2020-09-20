package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.controller.command.Сommand;
import com.zagurskaya.cash.entity.Role;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.model.service.RoleService;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.RoleServiceImpl;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.util.DataUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProfileСommand implements Сommand {
    private RoleService roleService = new RoleServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public Action execute(HttpServletRequest request) throws Exception {
        User user = DataUtil.findUser(request);
        if (user != null) {
//            List<Role> roles = new RoleDaoImpl().findAll();
            List<Role> roles = roleService.getAll();
            request.setAttribute("roles", roles);

//            List<User> users = new UserDaoImpl().findAll();
            List<User> users = userService.getAll();
            request.setAttribute("users", users);

            return Action.PROFILE;
        }
        return Action.LOGIN;
    }
}
