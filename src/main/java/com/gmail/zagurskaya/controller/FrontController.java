package com.gmail.zagurskaya.controller;

import com.gmail.zagurskaya.connection.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    static final Logger logger = LogManager.getLogger(FrontController.class);

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
        request.setAttribute("response", response);
        Action action = Action.define(request);
        try {
            Action nextAction = action.command.execute(request);
            if (nextAction == action) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(action.getJsp());
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect("do?command=" + nextAction.name().toLowerCase());
            }
        } catch (Exception e) {
            logger.log(Level.ERROR, "exception", e);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPoll();
    }
}
