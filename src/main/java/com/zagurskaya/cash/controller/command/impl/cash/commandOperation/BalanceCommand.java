package com.zagurskaya.cash.controller.command.impl.cash.commandOperation;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.util.RequestDataUtil;
import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.Kassa;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.DutiesService;
import com.zagurskaya.cash.model.service.KassaService;
import com.zagurskaya.cash.model.service.impl.DutiesServiceImpl;
import com.zagurskaya.cash.model.service.impl.KassaServiceImpl;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;


public class BalanceCommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(BalanceCommand.class);
    private final DutiesService dutiesService = new DutiesServiceImpl();
    private final KassaService kassaService =  new KassaServiceImpl();

    public BalanceCommand(String path) {
        super(path);
    }

    @Override
    public ActionType execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute("error");
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        try {
            ActionType actionType = actionAfterValidationUserAndPermission(request, ActionType.BALANCE);
            if (actionType == ActionType.BALANCE) {
                User user = RequestDataUtil.findUser(request);
                Duties duties = dutiesService.openDutiesUserToday(user, today);
                if (duties == null) {
                    return ActionType.DUTIES;
                }
                List<Kassa> balanceList = kassaService.getBallance(user, duties);
                request.setAttribute(AttributeName.BALANCE, balanceList);
                return ActionType.BALANCE;
            } else {
                return actionType;
            }
        } catch (ServiceException | NumberFormatException e) {
            session.setAttribute(AttributeName.ERROR, "100 " + e);
            logger.log(Level.ERROR, e);
            return ActionType.ERROR;
        }
    }
}
