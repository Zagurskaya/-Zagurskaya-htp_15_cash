package com.zagurskaya.cash.controller.command.impl.cash;

import com.zagurskaya.cash.controller.command.AbstractCommand;
import com.zagurskaya.cash.controller.command.ActionType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The action is "open/close duties".
 */
public class CurrencyCommand extends AbstractCommand {

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public CurrencyCommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) {
        return ActionType.CURRENCY;
    }
}
