package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.ActionType;

import javax.servlet.http.HttpServletRequest;

public class SelectPaymentCommand extends AbstractСommand {
    /**
     * Конструктор
     *
     * @param path - путь
     */
    public SelectPaymentCommand(String path) {
        super(path);
    }

    @Override
    public ActionType execute(HttpServletRequest req){
        return ActionType.SELECTPAYMENT;
    }
}
