package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.controller.util.RequestDataUtil;
import com.zagurskaya.cash.entity.RoleType;
import com.zagurskaya.cash.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Abstract class for all command.
 */
public abstract class AbstractCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AbstractCommand.class);
    private String directoryPath;

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public AbstractCommand(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public String getDirectoryPath() {
        return directoryPath;
    }

    /**
     * Checking the validity of the user and his permissions and returning the appropriate action.
     *
     * @param request - request
     * @return action
     */
    protected ActionType actionAfterValidationUserAndPermission(HttpServletRequest request, ActionType actionType) {
        final HttpSession session = request.getSession(false);
        RoleType actionPermission = ActionPermission.getInstance().getActionPermissionMap().get(actionType.name());

        User user = RequestDataUtil.findUser(request);
        if (user == null) {
            session.setAttribute(AttributeName.ERROR, "104 ");
            logger.log(Level.ERROR, "null user");
            return ActionType.ERROR;
        }
        if (user.getRole() != actionPermission) {
            session.setAttribute(AttributeName.ERROR, "103 ");
            logger.log(Level.ERROR, "permission denied ");
            return ActionType.INDEX;
        }
        return actionType;
    }

}
