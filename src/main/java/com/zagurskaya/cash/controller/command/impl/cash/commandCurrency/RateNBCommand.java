package com.zagurskaya.cash.controller.command.impl.cash.commandCurrency;

import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.RateNB;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.RateNBService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import com.zagurskaya.cash.model.service.impl.RateNBServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Действие "Курсы НБ".
 */
public class RateNBCommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(RateNBCommand.class);
    private final RateNBService rateNBService = new RateNBServiceImpl();
    private final CurrencyService currencyService = new CurrencyServiceImpl();

    /**
     * Конструктор
     *
     * @param directoryPath - путь
     */
    public RateNBCommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest request) {

        final HttpSession session = request.getSession(false);
        session.removeAttribute("error");

        try {
            ActionType actionType = actionAfterValidationUserAndPermission(request, ActionType.RATENB);
            if (actionType == ActionType.RATENB) {

                int page = 1;
                if (request.getParameter(AttributeName.PAGE) != null)
                    page = Integer.parseInt(request.getParameter(AttributeName.PAGE));

                int numberOfPages = (int) Math.ceil(rateNBService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
                List<RateNB> ratesNB = rateNBService.onePartOfListOnPage(page);

                List<Currency> currencyList = currencyService.findAll();

                request.setAttribute(AttributeName.NUMBER_OF_PAGE, numberOfPages);
                request.setAttribute(AttributeName.CURRENT_PAGE, page);
                request.setAttribute(AttributeName.RATE_NB, ratesNB);
                request.setAttribute(AttributeName.CURRENCIES, currencyList);

                return ActionType.RATENB;
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
