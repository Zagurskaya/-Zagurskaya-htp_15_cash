package com.zagurskaya.cash.controller.command.impl.cash.commandCurrency;

import com.zagurskaya.cash.constant.AttributeConstant;
import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllCurrencyCommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(AllCurrencyCommand.class);
    private final CurrencyService currencyService = new CurrencyServiceImpl();

    public AllCurrencyCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
//        List<Currency> currency = currencyService.onePartOfListOnPage();
//        req.setAttribute("currency", currency);
//        Action.ALLCURRENCY.setPATH("/cash/currency/");
//        return Action.ALLCURRENCY;

        final HttpSession session = request.getSession(false);
        session.removeAttribute("error");

        try {
            Action action = actionAfterValidationUserAndPermission(request, Action.ALLCURRENCY);
            if (action == Action.ALLCURRENCY) {

                int page = 1;
                if (request.getParameter(AttributeConstant.PAGE) != null)
                    page = Integer.parseInt(request.getParameter(AttributeConstant.PAGE));

                int numberOfPages = (int) Math.ceil(currencyService.countRows() * 1.0 / AttributeConstant.RECORDS_PER_PAGE);
                List<Currency> currencies = currencyService.onePartOfListOnPage(page);

                request.setAttribute(AttributeConstant.NUMBER_OF_PAGE, numberOfPages);
                request.setAttribute(AttributeConstant.CURRENT_PAGE, page);
                request.setAttribute(AttributeConstant.CURRENCIES, currencies);
                return Action.ALLCURRENCY;
            } else {
                return action;
            }
        } catch (ServiceException e) {
            session.setAttribute(AttributeConstant.ERROR, e);
            logger.log(Level.ERROR, e);
            return Action.ERROR;
        }
    }
}
