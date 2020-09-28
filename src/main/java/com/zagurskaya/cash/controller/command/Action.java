package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.controller.command.impl.ErrorСommand;
import com.zagurskaya.cash.controller.command.impl.IndexСommand;
import com.zagurskaya.cash.controller.command.impl.LocalEnСommand;
import com.zagurskaya.cash.controller.command.impl.LocalRuСommand;
import com.zagurskaya.cash.controller.command.impl.LoginСommand;
import com.zagurskaya.cash.controller.command.impl.PathConstant;
import com.zagurskaya.cash.controller.command.impl.ProfileСommand;
import com.zagurskaya.cash.controller.command.impl.admin.AdminCommand;
import com.zagurskaya.cash.controller.command.impl.admin.CreateUserCommand;
import com.zagurskaya.cash.controller.command.impl.admin.EditUsersCommand;
import com.zagurskaya.cash.controller.command.impl.admin.UpdateUserCommand;

import javax.servlet.http.HttpServletRequest;

public enum Action {
//  root
    INDEX(new IndexСommand(PathConstant.PATH_ROOT)),
    ERROR(new ErrorСommand(PathConstant.PATH_ROOT)),
    LOGIN(new LoginСommand(PathConstant.PATH_ROOT)),
    LOCALEN(new LocalEnСommand(PathConstant.PATH_ROOT)),
    LOCALRU(new LocalRuСommand(PathConstant.PATH_ROOT)),
    PROFILE(new ProfileСommand(PathConstant.PATH_ROOT)),
//  admin
    ADMIN(new AdminCommand(PathConstant.PATH_ADMIN)),
    EDITUSERS(new EditUsersCommand(PathConstant.PATH_ADMIN)),
    CREATEUSER(new CreateUserCommand(PathConstant.PATH_ADMIN)),
    UPDATEUSER(new UpdateUserCommand(PathConstant.PATH_ADMIN)),


    ;

    public Сommand command;

    Action(Сommand command) {
        this.command = command;
    }

    public String getJsp() {
        return command.getPath() + name().toLowerCase() + ".jsp";
    }

    public static Action define(HttpServletRequest request) {
        try {
            String command = request.getParameter("command").toUpperCase();
            return Action.valueOf(command);
        } catch (Exception e) {
            return Action.INDEX;
        }
    }
}
