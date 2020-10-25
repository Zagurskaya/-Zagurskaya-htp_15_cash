package com.zagurskaya.cash.controller.filter;

import com.zagurskaya.cash.controller.FrontController;
import com.zagurskaya.cash.controller.command.ActionPermission;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.util.ControllerDataUtil;
import com.zagurskaya.cash.entity.RoleType;
import com.zagurskaya.cash.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for validation of permissions
 */
public class PermissionFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(FrontController.class);
    public static final ThreadLocal<ActionType> THREAD_ACTION = new ThreadLocal<>();

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        ActionType currentActionType = ActionType.define(httpRequest);
        THREAD_ACTION.set(currentActionType);
        if (currentActionType != null && currentActionType != ActionType.INDEX
                && currentActionType != ActionType.LOGIN && currentActionType != ActionType.LOCALE) {
            User user = ControllerDataUtil.findUser(httpRequest);
            if (user == null) {
                session.setAttribute(AttributeName.ERROR, "104 ");
                logger.log(Level.ERROR, "null user");
                THREAD_ACTION.set(ActionType.ERROR);
            }
            RoleType actionPermission = ActionPermission.getInstance().getActionPermissionMap().get(currentActionType.name());
            if (user.getRole() != actionPermission && currentActionType != ActionType.PROFILE) {
                session.setAttribute(AttributeName.ERROR, "103 ");
                logger.log(Level.ERROR, "permission denied ");
                THREAD_ACTION.set(ActionType.INDEX);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}