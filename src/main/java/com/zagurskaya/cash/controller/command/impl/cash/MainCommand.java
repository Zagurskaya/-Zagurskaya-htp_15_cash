package com.zagurskaya.cash.controller.command.impl.cash;

import com.zagurskaya.cash.controller.command.AbstractCommand;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.AttributeName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The action is "Cashier Home".
 */
public class MainCommand extends AbstractCommand {

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public MainCommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute(AttributeName.MESSAGE);
        session.removeAttribute(AttributeName.ERROR);
        return ActionType.MAIN;
    }
}
