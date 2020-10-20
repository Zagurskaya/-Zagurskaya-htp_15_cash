package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.ActionType;

import javax.servlet.http.HttpServletRequest;

public class SelectPaymentCommand extends AbstractСommand {
    /**
     * Конструктор
     *
     * @param directoryPath - путь
     */
    public SelectPaymentCommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest req){
        return ActionType.SELECTPAYMENT;
    }
}
