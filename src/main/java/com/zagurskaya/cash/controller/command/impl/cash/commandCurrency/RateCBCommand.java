package com.zagurskaya.cash.controller.command.impl.cash.commandCurrency;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.RateCB;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.RateCBService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import com.zagurskaya.cash.model.service.impl.RateCBServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Действие "Курсы КБ".
 */
public class RateCBCommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(RateCBCommand.class);
    private final RateCBService rateCBService = new RateCBServiceImpl();
    private final CurrencyService currencyService = new CurrencyServiceImpl();

    /**
     * Конструктор
     *
     * @param path - путь
     */
    public RateCBCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {

        final HttpSession session = request.getSession(false);
        session.removeAttribute("error");

        try {
            Action action = actionAfterValidationUserAndPermission(request, Action.RATECB);
            if (action == Action.RATECB) {

                int page = 1;
                if (request.getParameter(AttributeName.PAGE) != null)
                    page = Integer.parseInt(request.getParameter(AttributeName.PAGE));

                int numberOfPages = (int) Math.ceil(rateCBService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
                List<RateCB> ratesCB = rateCBService.onePartOfListOnPage(page);

                List<Currency> currencyList = currencyService.findAll();

                request.setAttribute(AttributeName.NUMBER_OF_PAGE, numberOfPages);
                request.setAttribute(AttributeName.CURRENT_PAGE, page);
                request.setAttribute(AttributeName.RATE_CB, ratesCB);
                request.setAttribute(AttributeName.CURRENCIES, currencyList);

                return Action.RATECB;
            } else {
                return action;
            }
        } catch (ServiceException e) {
            session.setAttribute(AttributeName.ERROR, e);
            logger.log(Level.ERROR, e);
            return Action.ERROR;
        }
    }
}
