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
 * Команда со свойствами <b>directoryPath</b>.
 */
public abstract class AbstractСommand implements Сommand {
    private static final Logger logger = LogManager.getLogger(AbstractСommand.class);
    private String directoryPath;

    /**
     * Конструктор
     *
     * @param directoryPath - путь
     */
    public AbstractСommand(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public String getDirectoryPath() {
        return directoryPath;
    }

    /**
     * Проверка валидности пользователя и его прав и возвращение сооьветствующего действия
     *
     * @param request - запрос
     * @return действие
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
