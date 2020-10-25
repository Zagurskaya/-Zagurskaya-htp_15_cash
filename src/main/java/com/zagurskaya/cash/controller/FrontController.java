package com.zagurskaya.cash.controller;

import com.zagurskaya.cash.controller.filter.PermissionFilter;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.model.pool.ConnectionPool;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.AttributeName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Action controller
 */
public class FrontController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(FrontController.class);
    private static final String DO_COMMAND = "do?command=";

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        ActionType currentActionType = ActionType.define(request);
        try {
            ActionType permissionActionType = PermissionFilter.THREAD_ACTION.get();
            if (permissionActionType != null && permissionActionType != currentActionType) {
                response.sendRedirect(DO_COMMAND + permissionActionType.name().toLowerCase());
            } else {
                ActionType nextActionType = currentActionType.getCommand().execute(request, response);
                session.setAttribute(AttributeName.CURRENT_ACTION_TYPE, currentActionType);
                if (nextActionType == currentActionType) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(currentActionType.getJsp());
                    requestDispatcher.forward(request, response);
                } else {
                    response.sendRedirect(DO_COMMAND + nextActionType.name().toLowerCase());
                }
            }
        } catch (CommandException e) {
            String error = e.getMessage();
            request.setAttribute(AttributeName.ERROR, error);
            logger.log(Level.ERROR, error, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(currentActionType.getJsp());
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            session.setAttribute(AttributeName.ERROR, "100 " + e);
            logger.log(Level.ERROR, e);
            response.sendRedirect(DO_COMMAND + ActionType.ERROR);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPoll();
    }
}
