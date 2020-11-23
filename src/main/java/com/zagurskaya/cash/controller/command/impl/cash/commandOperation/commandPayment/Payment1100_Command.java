package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.Command;
import com.zagurskaya.cash.util.ControllerDataUtil;
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
import com.zagurskaya.cash.util.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * The action is "Payment 1100".
 */
public class Payment1100_Command implements Command {
    private String directoryPath;
    private static final Logger logger = LogManager.getLogger(Payment1100_Command.class);
    private final DutiesService dutiesService = new DutiesServiceImpl();
    private final CurrencyService currencyService = new CurrencyServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();


    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public Payment1100_Command(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public String getDirectoryPath() {
        return directoryPath;
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ControllerDataUtil.removeAttributeError(request);
        ControllerDataUtil.removeAttributeMessage(request);
        final HttpSession session = request.getSession(false);


        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        try {

            User user = ControllerDataUtil.findUser(request);
            if (dutiesService.openDutiesUserToday(user, today) == null) {
                return ActionType.DUTIES;
            }
            if (DataValidation.isCreateUpdateDeleteOperation(request)) {
                if (DataValidation.isСontinueOperation(request)) {

                    Map<Long, Double> values = ControllerDataUtil.getMapLongDouble(request, AttributeName.ID, AttributeName.SUM);
                    String specification = ControllerDataUtil.getString(request, AttributeName.SPECIFICATION);

                    if (values.isEmpty()) {
                        request.setAttribute(AttributeName.MESSAGE, "Empty value");
                        List<Currency> currencies = currencyService.findAll();
                        request.setAttribute(AttributeName.CURRENCIES, currencies);
                        return ActionType.PAYMENT1100;
                    }
                    paymentService.implementPayment1100(values, specification, user);
                    session.setAttribute(AttributeName.MESSAGE, "109 ");
                    return ActionType.PAYMENT;

                } else if (DataValidation.isGetBalanceOperation(request)) {
                    return ActionType.PAYMENT1100BALANCE;

                }
            }

            List<Currency> currencies = currencyService.findAll();
            request.setAttribute(AttributeName.CURRENCIES, currencies);
            return ActionType.PAYMENT1100;

        } catch (ServiceException | NumberFormatException e) {
            session.setAttribute(AttributeName.ERROR, "100 " + e);
            logger.log(Level.ERROR, e);
            return ActionType.ERROR;
        }
    }
}
