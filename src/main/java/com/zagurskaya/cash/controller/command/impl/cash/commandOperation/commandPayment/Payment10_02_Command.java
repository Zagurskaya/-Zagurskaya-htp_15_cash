package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.Command;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.PDFDocument;
import com.zagurskaya.cash.controller.util.ControllerDataUtil;
import com.zagurskaya.cash.controller.util.DataValidation;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.DutiesService;
import com.zagurskaya.cash.model.service.PaymentService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import com.zagurskaya.cash.model.service.impl.DutiesServiceImpl;
import com.zagurskaya.cash.model.service.impl.PaymentServiceImpl;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * The action is "Payment 1002".
 */
public class Payment10_02_Command implements Command {
    private String directoryPath;
    private static final Logger logger = LogManager.getLogger(Payment10_02_Command.class);
    private final DutiesService dutiesService = new DutiesServiceImpl();
    private final CurrencyService currencyService = new CurrencyServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();
    private static final int NUMBER_OPERATION = 10;

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public Payment10_02_Command(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public String getDirectoryPath() {
        return directoryPath;
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ControllerDataUtil.removeAttributeError(request);
        String today = DataUtil.getFormattedLocalDateStartDateTime(LocalDate.now());

        try {
            HttpSession session = request.getSession(false);
            List<Currency> currencies = currencyService.findAll();
            Long currencyIdSession = (Long) session.getAttribute(AttributeName.CURRENCY_ID);
            Long currencySumSession = (Long) session.getAttribute(AttributeName.CURRENCY_SUM);
            Double rateCBPaymentSession = (Double) session.getAttribute(AttributeName.RATE_CB_PAYMENT);
            Double sumRateCurrencyIdSession = (Double) session.getAttribute(AttributeName.SUM_RATE_CURRENCY_ID);
            String specificationSession = (String) session.getAttribute(AttributeName.SPECIFICATION);

            request.setAttribute(AttributeName.CURRENCY_ID, currencyIdSession);
            request.setAttribute(AttributeName.CURRENCY_SUM, currencySumSession);
            request.setAttribute(AttributeName.RATE_CB_PAYMENT, rateCBPaymentSession);
            request.setAttribute(AttributeName.SUM_RATE_CURRENCY_ID, Math.round(sumRateCurrencyIdSession * 100.0) / 100.0);
            request.setAttribute(AttributeName.SPECIFICATION, specificationSession);
            request.setAttribute(AttributeName.CURRENCIES, currencies);

            User user = ControllerDataUtil.findUser(request);
            if (dutiesService.openDutiesUserToday(user, today) == null) {
                return ActionType.DUTIES;
            }
            if (DataValidation.isCreateUpdateDeleteOperation(request)) {
                Map<Long, Double> values = ControllerDataUtil.getMapLongDouble(request, AttributeName.ID, AttributeName.SUM);
                String specification = ControllerDataUtil.getString(request, AttributeName.SPECIFICATION);

                if (values.isEmpty()) {
                    return ActionType.PAYMENT;
                }
                Long operationId = paymentService.implementPayment10(values, rateCBPaymentSession, specification, user);
                Cookie localeCookie = ControllerDataUtil.getCookie(request, AttributeName.LOCAL);
                String locale = localeCookie != null && (!localeCookie.getValue().equals(AttributeName.LOCALE_RU)) ? AttributeName.LOCALE_EN : AttributeName.LOCALE_RU;
                PDFDocument document = new CheckOperation10();
                document.createCheck(operationId, NUMBER_OPERATION, locale);
                return ActionType.PAYMENT;
            }

            return ActionType.PAYMENT10_02;

        } catch (ServiceException | NumberFormatException e) {
            request.getSession(false).setAttribute(AttributeName.ERROR, "100 " + e);
            logger.log(Level.ERROR, e);
            return ActionType.ERROR;
        }
    }
}
