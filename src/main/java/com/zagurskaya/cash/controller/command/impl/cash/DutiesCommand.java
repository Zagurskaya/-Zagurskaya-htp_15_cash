package com.zagurskaya.cash.controller.command.impl.cash;

import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.Command;
import com.zagurskaya.cash.util.ControllerDataUtil;
import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.DutiesService;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.DutiesServiceImpl;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.util.DataUtil;
import com.zagurskaya.cash.util.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

public class DutiesCommand implements Command {
    private String directoryPath;
    private static final Logger logger = LogManager.getLogger(DutiesCommand.class);
    private final DutiesService dutiesService = new DutiesServiceImpl();
    private final UserService userService = new UserServiceImpl();

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public DutiesCommand(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public String getDirectoryPath() {
        return directoryPath;
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) {
        ControllerDataUtil.removeAttributeError(request);
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        try {
            User user = ControllerDataUtil.findUser(request);

            if (DataValidation.isCreateUpdateDeleteOperation(request)) {
                if (DataValidation.isOpenOperation(request)) {
                    if (dutiesService.openDutiesUserToday(user, today) == null) {
                        dutiesService.openNewDuties(user);
                    }
                    setAttributeToRequest(request, user);
                    return ActionType.DUTIES;
                } else if (DataValidation.isCloseOperation(request)) {
                    dutiesService.closeOpenDutiesUserToday(user);
                    setAttributeToRequest(request, user);
                    return ActionType.DUTIES;
                }
            }
            setAttributeToRequest(request, user);
            return ActionType.DUTIES;
        } catch (ServiceException e) {
            request.getSession(false).setAttribute(AttributeName.ERROR, "100 " + e);
            logger.log(Level.ERROR, e);
            return ActionType.ERROR;
        }
    }

    private void setAttributeToRequest(HttpServletRequest request, User user) throws ServiceException {
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);

        List<User> users = userService.findAll();
        Duties duties = dutiesService.openDutiesUserToday(user, today);
        String messageDuties = (duties != null) ? "201 " + user.getFullName() : "202 " + user.getFullName();


        request.setAttribute(AttributeName.USERS, users);
        request.setAttribute(AttributeName.DUTIES, duties);
        request.setAttribute(AttributeName.DUTIES_MESSAGE, messageDuties);
    }
}
