package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.Command;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.util.ControllerDataUtil;
import com.zagurskaya.cash.controller.util.DataValidation;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.DutiesService;
import com.zagurskaya.cash.model.service.RateCBService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import com.zagurskaya.cash.model.service.impl.DutiesServiceImpl;
import com.zagurskaya.cash.model.service.impl.RateCBServiceImpl;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * The action is "Payment 1001".
 */
public class Payment10_01_Command implements Command {
    private String directoryPath;
    private static final Logger logger = LogManager.getLogger(Payment10_01_Command.class);
    private final DutiesService dutiesService = new DutiesServiceImpl();
    private final CurrencyService currencyService = new CurrencyServiceImpl();
    private final RateCBService rateCBService = new RateCBServiceImpl();


    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public Payment10_01_Command(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public String getDirectoryPath() {
        return directoryPath;
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ControllerDataUtil.removeAttributeError(request);
        LocalDate date = LocalDate.now();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        try {
            User user = ControllerDataUtil.findUser(request);
            if (dutiesService.openDutiesUserToday(user, today) == null) {
                return ActionType.DUTIES;
            }
            if (DataValidation.isCreateUpdateDeleteOperation(request)) {
                Long currencyId = ControllerDataUtil.getLong(request, AttributeName.ID);
                Long currencySum = ControllerDataUtil.getLong(request, AttributeName.SUM);
                String specification = ControllerDataUtil.getString(request, AttributeName.SPECIFICATION);
                if (currencyId == null || currencySum == null) {
                    return ActionType.PAYMENT10_01;
                }
                Double rateCBPayment = rateCBService.rateCBToday(now, currencyId, AttributeName.NС);
                Double sumRateCurrencyId = rateCBPayment * currencySum;

                HttpSession session = request.getSession(false);
                session.setAttribute("currencyId", currencyId);
                session.setAttribute("currencySum", currencySum);
                session.setAttribute("rateCBPayment", rateCBPayment);
                session.setAttribute("sumRateCurrencyId", sumRateCurrencyId);
                session.setAttribute("specification", specification);

                return ActionType.PAYMENT10_02;
            }

            List<Currency> currencies = currencyService.findAll();
            request.getSession(false).setAttribute(AttributeName.CURRENCIES, currencies);
            return ActionType.PAYMENT10_01;

        } catch (ServiceException | NumberFormatException e) {
            request.getSession(false).setAttribute(AttributeName.ERROR, "100 " + e);
            logger.log(Level.ERROR, e);
            return ActionType.ERROR;
        }
    }
}
