package com.zagurskaya.cash.controller.command.impl.cash.commandOperation;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.Command;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.util.ControllerDataUtil;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

/**
 * The action is "Balance".
 */
public class BalanceCommand implements Command {
    private String directoryPath;
    private static final Logger logger = LogManager.getLogger(BalanceCommand.class);
    private final DutiesService dutiesService = new DutiesServiceImpl();
    private final KassaService kassaService = new KassaServiceImpl();

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public BalanceCommand(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public String getDirectoryPath() {
        return directoryPath;
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute(AttributeName.ERROR);
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        try {
            User user = ControllerDataUtil.findUser(request);
            Duties duties = dutiesService.openDutiesUserToday(user, today);
            if (duties == null) {
                return ActionType.DUTIES;
            }
            List<Kassa> balanceList = kassaService.getBalance(user, duties);
            request.setAttribute(AttributeName.BALANCE, balanceList);
            return ActionType.BALANCE;

        } catch (ServiceException | NumberFormatException e) {
            session.setAttribute(AttributeName.ERROR, "100 " + e);
            logger.log(Level.ERROR, e);
            return ActionType.ERROR;
        }
    }
}
