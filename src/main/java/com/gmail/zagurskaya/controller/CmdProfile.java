package com.gmail.zagurskaya.controller;

import com.gmail.zagurskaya.beans.Role;
import com.gmail.zagurskaya.beans.User;
import com.gmail.zagurskaya.dao.impl.RoleDaoImpl;
import com.gmail.zagurskaya.dao.impl.UserDaoImpl;
import com.gmail.zagurskaya.util.DataUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CmdProfile implements Cmd {
    @Override
    public Action execute(HttpServletRequest req) throws Exception {
        User user = DataUtil.findUser(req);
        if (user != null) {
            List<Role> roles = new RoleDaoImpl().getAll();
            req.setAttribute("roles", roles);

            List<User> users = new UserDaoImpl().getAll();
            req.setAttribute("users", users);

            return Action.PROFILE;
        }
        return Action.LOGIN;
    }
}
