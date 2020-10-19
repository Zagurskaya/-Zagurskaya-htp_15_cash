package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.controller.util.RequestDataUtil;
import com.zagurskaya.cash.entity.RoleEnum;
import com.zagurskaya.cash.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Команда со свойствами <b>path</b>.
 */
public abstract class AbstractСommand implements Сommand {
    private static final Logger logger = LogManager.getLogger(AbstractСommand.class);
    private final String path;

    /**
     * Конструктор
     *
     * @param path - путь
     */
    public AbstractСommand(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    /**
     * Проверка валидности пользователя и его прав и возвращение сооьветствующего действия
     *
     * @param request - запрос
     * @return действие
     */
    protected Action actionAfterValidationUserAndPermission(HttpServletRequest request, Action action) {
        final HttpSession session = request.getSession(false);
        RoleEnum actionPermission = ActionPermission.getInstance().getActionPermissionMap().get(action.name());

        User user = RequestDataUtil.findUser(request);
        if (user == null) {
            session.setAttribute(AttributeName.ERROR, "104 ");
            logger.log(Level.ERROR, "null user");
            return Action.ERROR;
        }
        if (user.getRole() != actionPermission) {
            session.setAttribute(AttributeName.ERROR, "103 ");
            logger.log(Level.ERROR, "permission denied ");
            return Action.INDEX;
        }
        return action;
    }

}
