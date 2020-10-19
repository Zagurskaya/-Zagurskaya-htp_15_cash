package com.zagurskaya.cash.controller.command.impl.cash.commandOperation;

import com.zagurskaya.cash.constant.AttributeConstant;
import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.SprOperation;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.entity.UserOperation;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class UserOperationsCommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(UserOperationsCommand.class);
    private final DutiesService dutiesService = new DutiesServiceImpl();
    private final CurrencyService currencyService = new CurrencyServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();

    public UserOperationsCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute("error");
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        try {
            Action action = actionAfterValidationUserAndPermission(request, Action.USEROPERATIONS);
            if (action == Action.USEROPERATIONS) {
                User user = DataUtil.findUser(request);
                Duties duties = dutiesService.openDutiesUserToday(user, today);
                if (duties == null) {
                    return Action.DUTIES;
                }
                List<UserOperation> userAllOperationList = paymentService.getUserOperationsByUserAndDuties(user, duties);
                request.setAttribute(AttributeConstant.USER_OPERATIONS, userAllOperationList);

                List<SprOperation> sprOperationsList = paymentService.findAllSprOperation();
                request.setAttribute(AttributeConstant.SPR_OPERATIONS, sprOperationsList);

                List<Currency> currencyList = currencyService.findAll();
                request.setAttribute(AttributeConstant.CURRENCIES, currencyList);

                return Action.USEROPERATIONS;
            } else {
                return action;
            }
        } catch (ServiceException | NumberFormatException e) {
            session.setAttribute(AttributeConstant.ERROR, "100 " + e);
            logger.log(Level.ERROR, e);
            return Action.ERROR;
        }
    }
}
