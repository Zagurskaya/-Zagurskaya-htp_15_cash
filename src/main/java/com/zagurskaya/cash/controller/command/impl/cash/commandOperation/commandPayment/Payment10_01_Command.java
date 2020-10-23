package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import com.zagurskaya.cash.controller.command.AbstractCommand;
import com.zagurskaya.cash.controller.command.ActionType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The action is "Payment 1001".
 */
public class Payment10_01_Command extends AbstractCommand {

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public Payment10_01_Command(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest req, HttpServletResponse response) {
//        LocalDate date = LocalDate.now();
//        Timestamp now = new Timestamp(System.currentTimeMillis());
//
//        CurrencyDao currencyDao = new CurrencyDao();
//        List<Currency> currencies = currencyDao.getAll();
//        req.setAttribute("currencies", currencies);
//
//        if (Form.isPost(req)) {
//
//            long currencyId = Form.getLong(req, "id");
//            long currencySum = Form.getLong(req, "sum");
//            String specification = Form.getString(req, "specification");
//
//            RateCBDao rateСBDao = new RateCBDao();
//            double rateCBPayment = rateСBDao.rateCBToday(now, currencyId, 933);
//            double sumRateCurrencyId = rateCBPayment * currencySum;
//            HttpSession session = req.getSession(false);
//            session.setAttribute("currencyId", currencyId);
//            session.setAttribute("currencySum", currencySum);
//            session.setAttribute("rateCBPayment", rateCBPayment);
//            session.setAttribute("sumRateCurrencyId", sumRateCurrencyId);
//            session.setAttribute("specification", specification);
//
//            Action.PAYMENT10_02.setPATH("/cash/operation/payment/");
//            return Action.PAYMENT10_02;
//        }
//        Action.PAYMENT10_01.setPATH("/cash/operation/payment/");
        return ActionType.PAYMENT10_01;
    }
}
