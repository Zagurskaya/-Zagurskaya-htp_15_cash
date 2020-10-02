package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.entity.RoleEnum;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.util.AttributeConstant;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractСommand implements Сommand {
    private static final Logger logger = LogManager.getLogger(AbstractСommand.class);
    private final String path;

    public AbstractСommand(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    protected Action actionAfterValidationUserAndPermission(HttpServletRequest request, Action action) {
        final HttpSession session = request.getSession(false);
        RoleEnum actionPermission = ActionPermission.getInstance().getActionPermissionMap().get(action.name());

        User user = DataUtil.findUser(request);
        if (user == null) {
            session.setAttribute(AttributeConstant.ERROR, "null user");
            logger.log(Level.ERROR, "null user");
            return Action.ERROR;
        }
        if (user != null && !(user.getRole() == actionPermission)) {
            session.setAttribute(AttributeConstant.ERROR, "permission denied to the admin's directory");
            logger.log(Level.ERROR, "permission denied to the admin's directory");
            return Action.INDEX;
        }
        return action;
    }

}
