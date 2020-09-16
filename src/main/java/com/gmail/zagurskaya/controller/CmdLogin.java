package com.gmail.zagurskaya.controller;

import com.gmail.zagurskaya.beans.User;
import com.gmail.zagurskaya.dao.UserDao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class CmdLogin implements Cmd {
    @Override
    public Action execute(HttpServletRequest req) throws Exception {
        if (Form.isPost(req)) {
            String login = Form.getString(req, "login", "[a-zA-Z0-9_-]{5,}");
            String password = Form.getString(req, "password", "[a-zA-Z0-9_-]{5,}");
            String where = String.format(
                    " WHERE `login`='%s' AND `password`='%s'",
                    login, password);
            UserDao userDao = new UserDao();
            List<User> users = userDao.getAll(where);
            if (users.size() > 0) {
                User user = users.get(0);
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
//                Cookie cookie = new Cookie("hash", Util.getHash(user));
//                Util.setCookie(req, cookie);
//                cookie = new Cookie("login", user.getLogin());
//                Util.setCookie(req, cookie);


                Action.PROFILE.setPATH("/");
                return Action.PROFILE;
            }
        }
        Action.LOGIN.setPATH("/");
        return Action.LOGIN;
    }
}
