package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;

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
    public Action execute(HttpServletRequest req){
        return Action.SELECTPAYMENT;
    }
}
