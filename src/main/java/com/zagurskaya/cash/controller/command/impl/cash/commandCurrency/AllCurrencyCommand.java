package com.zagurskaya.cash.controller.command.impl.cash.commandCurrency;

import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.AbstractСommand;
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

/**
 * Действие "Валюта".
 */
public class AllCurrencyCommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(AllCurrencyCommand.class);
    private final CurrencyService currencyService = new CurrencyServiceImpl();

    /**
     * Конструктор
     *
     * @param path - путь
     */
    public AllCurrencyCommand(String path) {
        super(path);
    }

    @Override
    public ActionType execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute("error");

        try {
            ActionType actionType = actionAfterValidationUserAndPermission(request, ActionType.ALLCURRENCY);
            if (actionType == ActionType.ALLCURRENCY) {

                int page = 1;
                if (request.getParameter(AttributeName.PAGE) != null)
                    page = Integer.parseInt(request.getParameter(AttributeName.PAGE));

                int numberOfPages = (int) Math.ceil(currencyService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
                List<Currency> currencies = currencyService.onePartOfListOnPage(page);

                request.setAttribute(AttributeName.NUMBER_OF_PAGE, numberOfPages);
                request.setAttribute(AttributeName.CURRENT_PAGE, page);
                request.setAttribute(AttributeName.CURRENCIES, currencies);
                return ActionType.ALLCURRENCY;
            } else {
                return actionType;
            }
        } catch (ServiceException e) {
            session.setAttribute(AttributeName.ERROR, e);
            logger.log(Level.ERROR, e);
            return ActionType.ERROR;
        }
    }
}
