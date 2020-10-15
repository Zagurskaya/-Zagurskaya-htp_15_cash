package com.zagurskaya.cash.controller.command.impl.cash.commandCurrency;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;

import javax.servlet.http.HttpServletRequest;

public class RateNBCommand  extends AbstractСommand {

    public RateNBCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest req)  {
//        List<RateNB> rateNB = new RateNBDao().getAll();
//        req.setAttribute("rateNB", rateNB);
//
//        List<Currency> currencyList = new CurrencyDao().getAll();
//        req.setAttribute("currencyList", currencyList);
//
//        Action.RATENB.setPATH("/cash/currency/");
        return Action.RATENB;
    }
}
