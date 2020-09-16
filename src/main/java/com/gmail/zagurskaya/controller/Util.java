package com.gmail.zagurskaya.controller;

import com.gmail.zagurskaya.beans.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

    public static User findUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null)
            return null;
        Object oUser = session.getAttribute("user");
        if (oUser == null) {
            return null;
        } else {
            return (User) oUser;
        }
    }

    public static String getHash(User user){
        String key=user.getLogin()+user.getPassword();
        key=key+"qqqqqqqq";
        //todo
//        byte[] hash = DigestUtils.md5(key);
        byte[] hash = null;
        StringBuilder sb=new StringBuilder();
        for (byte b : hash) {
            sb.append(b);
        }
        return sb.toString();
    }

    static void setCookie(HttpServletRequest req, Cookie cookie){
        HttpServletResponse resp = (HttpServletResponse) req.getAttribute("response");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }

    public static String getFormattedLocalDateStartDateTime(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.000"));
    }

    public static String getFormattedLocalDateOnlyDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    public static double round(double sum) {
        return Math.round(sum * 100.0) / 100.0;
    }
}
