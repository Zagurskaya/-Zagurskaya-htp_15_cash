package com.zagurskaya.cash.controller.command.impl.cash.commandOperation;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.controller.util.RequestDataUtil;
import com.zagurskaya.cash.entity.SprOperation;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.DutiesService;
import com.zagurskaya.cash.model.service.PaymentService;
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

public class PaymentCommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(PaymentCommand.class);
    private final DutiesService dutiesService = new DutiesServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();
    private static final String SPR_OPERATION_ID = "SprOperationsId";

    public PaymentCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute("error");
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        try {
            Action action = actionAfterValidationUserAndPermission(request, Action.PAYMENT);
            if (action == Action.PAYMENT) {
                User user = RequestDataUtil.findUser(request);
                if (dutiesService.openDutiesUserToday(user, today) == null) {
                    return Action.DUTIES;
                }
                if (DataValidation.isCreateUpdateDeleteOperation(request)) {
                    Long sprOperationsId = DataUtil.getLong(request, SPR_OPERATION_ID);

                    if (sprOperationsId != null) {
                        SprOperation sprOperation = paymentService.findSprOperationById(sprOperationsId);
                        if (sprOperation != null) {
                            request.setAttribute(AttributeName.SPR_OPERATION, sprOperation);
                        }
                        switch (sprOperationsId.toString()) {
                            case "10":
                                return Action.PAYMENT10_01;
                            case "20":
                                return Action.PAYMENT20_01;
                            case "998":
                                return Action.PAYMENT998;
                            case "1000":
                                return Action.PAYMENT1000;
                            case "1100":
                                return Action.PAYMENT1100;
                            default:
                                return Action.PAYMENT;
                        }
                    }
                }
                List<SprOperation> sprOperations = paymentService.findAllSprOperation();
                request.setAttribute(AttributeName.SPR_OPERATIONS, sprOperations);
                return Action.PAYMENT;
            } else {
                return action;
            }
        } catch (ServiceException | NumberFormatException e) {
            session.setAttribute(AttributeName.ERROR, "100 " + e);
            logger.log(Level.ERROR, e);
            return Action.ERROR;
        }
    }

}
