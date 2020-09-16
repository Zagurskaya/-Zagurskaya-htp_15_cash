package com.gmail.zagurskaya.controller;

import com.gmail.zagurskaya.beans.User;
import com.gmail.zagurskaya.dao.UserDao;
import com.gmail.zagurskaya.dao.impl.UserDaoImpl;
import com.gmail.zagurskaya.util.Form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CmdLogin implements Cmd {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public Action execute(HttpServletRequest req) throws Exception {
        if (Form.isPost(req)) {
            String login = Form.getString(req, "login", "[a-zA-Z0-9_-]{5,}");
            String password = Form.getString(req, "password", "[a-zA-Z0-9_-]{5,}");
            User user = userDao.getUserByLoginAndPassword(login, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                Action.PROFILE.setPATH("/");
                return Action.PROFILE;
            }
        }
        Action.LOGIN.setPATH("/");
        return Action.LOGIN;
    }
}
