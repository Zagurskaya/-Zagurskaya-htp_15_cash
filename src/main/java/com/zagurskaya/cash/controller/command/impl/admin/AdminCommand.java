package com.zagurskaya.cash.controller.command.impl.admin;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AdminCommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(AdminCommand.class);

    public AdminCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        User user = DataUtil.findUser(request);
        if (user != null) {
            return Action.ADMIN;
        } else {
            logger.log(Level.ERROR, "null user");
            return Action.ERROR;
        }
    }
}
