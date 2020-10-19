package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.controller.util.RequestDataUtil;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.exception.SiteDataValidationException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.DutiesService;
import com.zagurskaya.cash.model.service.PaymentService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import com.zagurskaya.cash.model.service.impl.DutiesServiceImpl;
import com.zagurskaya.cash.model.service.impl.PaymentServiceImpl;
import com.zagurskaya.cash.util.DataUtil;
import com.zagurskaya.cash.controller.util.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class Payment1000_Command extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(Payment1000_Command.class);
    private final DutiesService dutiesService = new DutiesServiceImpl();
    private final CurrencyService currencyService = new CurrencyServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();

    /**
     * Конструктор
     *
     * @param path - путь
     */
    public Payment1000_Command(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute("error");
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        try {
            Action action = actionAfterValidationUserAndPermission(request, Action.PAYMENT1000);
            if (action == Action.PAYMENT1000) {
                User user = RequestDataUtil.findUser(request);
                if (dutiesService.openDutiesUserToday(user, today) == null) {
                    return Action.DUTIES;
                }
                if (DataValidation.isCreateUpdateDeleteOperation(request)) {
                    Map<Long, Double> values = RequestDataUtil.getMapLongDouble(request, "id", "sum");
                    String specification = RequestDataUtil.getString(request, "specification");

                    if (values.isEmpty()) {
                        return Action.PAYMENT;
                    }
                    paymentService.implementPayment1000(values, specification, user);
                    //todo change to check
                    return Action.PAYMENT;
                }

                List<Currency> currencies = currencyService.findAll();
                request.setAttribute(AttributeName.CURRENCIES, currencies);
                return Action.PAYMENT1000;
            } else {
                return action;
            }
        } catch (ServiceException | NumberFormatException | SiteDataValidationException | ServiceConstraintViolationException e) {
            session.setAttribute(AttributeName.ERROR, "100 " + e);
            logger.log(Level.ERROR, e);
            return Action.ERROR;
        }
    }
}
