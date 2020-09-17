package com.gmail.zagurskaya.controller;

import com.gmail.zagurskaya.beans.Role;
import com.gmail.zagurskaya.beans.User;
import com.gmail.zagurskaya.dao.impl.RoleDaoImpl;
import com.gmail.zagurskaya.dao.impl.UserDaoImpl;
import com.gmail.zagurskaya.service.RoleService;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.impl.RoleServiceImpl;
import com.gmail.zagurskaya.service.impl.UserServiceImpl;
import com.gmail.zagurskaya.util.DataUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CmdProfile implements Cmd {
    private RoleService roleService = new RoleServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public Action execute(HttpServletRequest req) throws Exception {
        User user = DataUtil.findUser(req);
        if (user != null) {
//            List<Role> roles = new RoleDaoImpl().getAll();
            List<Role> roles = roleService.getAll();
            req.setAttribute("roles", roles);

//            List<User> users = new UserDaoImpl().getAll();
            List<User> users = userService.getAll();
            req.setAttribute("users", users);

            return Action.PROFILE;
        }
        return Action.LOGIN;
    }
}
