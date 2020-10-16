package com.zagurskaya.cash.controller.command.impl.cash;

import com.zagurskaya.cash.constant.AttributeConstant;
import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.DutiesService;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.DutiesServiceImpl;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.util.DataUtil;
import com.zagurskaya.cash.validation.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class DutiesCommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(DutiesCommand.class);
    private final DutiesService dutiesService = new DutiesServiceImpl();
    private final UserService userService = new UserServiceImpl();

    public DutiesCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute("error");
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        try {
            Action action = actionAfterValidationUserAndPermission(request, Action.DUTIES);
            if (action == Action.DUTIES) {
                User user = DataUtil.findUser(request);

                if (DataValidation.isCreateUpdateDeleteOperation(request)) {
                    if (DataValidation.isOpenOperation(request)) {
                        if (dutiesService.openDutiesUserToday(user, today) == null) {
                            dutiesService.openNewDuties(user);
                        }
                        setAttributeToRequest(request, user);
                        return Action.DUTIES;
                    } else if (DataValidation.isCloseOperation(request)) {
                        dutiesService.closeOpenDutiesUserToday(user);
                        setAttributeToRequest(request, user);
                        return Action.DUTIES;
                    }
                }
                setAttributeToRequest(request, user);
                return Action.DUTIES;
            } else {
                return action;
            }
        } catch (ServiceException | ServiceConstraintViolationException e) {
            session.setAttribute(AttributeConstant.ERROR, "100 " + e);
            logger.log(Level.ERROR, e);
            return Action.ERROR;
        }
    }

    private void setAttributeToRequest(HttpServletRequest request, User user) throws ServiceException {
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);

        List<User> users = userService.findAll();
        Duties duties = dutiesService.openDutiesUserToday(user, today);
        String messageDuties = (duties != null) ? "201 " + user.getFullName() : "202 " + user.getFullName();


        request.setAttribute(AttributeConstant.USERS, users);
        request.setAttribute(AttributeConstant.DUTIES, duties);
        request.setAttribute(AttributeConstant.DUTIES_MESSAGE, messageDuties);
    }
}
