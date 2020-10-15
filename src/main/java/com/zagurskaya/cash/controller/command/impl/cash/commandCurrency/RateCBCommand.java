package com.zagurskaya.cash.controller.command.impl.cash.commandCurrency;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;

import javax.servlet.http.HttpServletRequest;

public class RateCBCommand extends AbstractСommand {
    public RateCBCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest req) {
//        List<RateCB> rateCB = new RateCBDao().getAll();
//        req.setAttribute("rateCB", rateCB);
//
//        List<Currency> currencyList = new CurrencyDao().getAll();
//        req.setAttribute("currencyList", currencyList);
//
//        Action.RATECB.setPATH("/cash/currency/");
        return Action.RATECB;
    }
}
