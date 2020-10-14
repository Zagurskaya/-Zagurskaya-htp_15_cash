package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.constant.AttributeConstant;
import com.zagurskaya.cash.controller.command.impl.ErrorСommand;
import com.zagurskaya.cash.controller.command.impl.IndexСommand;
import com.zagurskaya.cash.controller.command.impl.LocaleEnСommand;
import com.zagurskaya.cash.controller.command.impl.LocaleRuСommand;
import com.zagurskaya.cash.controller.command.impl.LoginСommand;
import com.zagurskaya.cash.controller.command.impl.LogoutСommand;
import com.zagurskaya.cash.controller.command.impl.PathConstant;
import com.zagurskaya.cash.controller.command.impl.ProfileСommand;
import com.zagurskaya.cash.controller.command.impl.admin.AdminCommand;
import com.zagurskaya.cash.controller.command.impl.admin.CreateUserCommand;
import com.zagurskaya.cash.controller.command.impl.admin.EditUsersCommand;
import com.zagurskaya.cash.controller.command.impl.admin.UpdateUserCommand;
import com.zagurskaya.cash.controller.command.impl.cash.MainCommand;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Перечень действий
 */
public enum Action {
//  root
    /**
     * Стартовая страница
     */
    INDEX(new IndexСommand(PathConstant.PATH_INDEX)),
    /**
     * Ошибки
     */
    ERROR(new ErrorСommand(PathConstant.PATH_ROOT)),
    /**
     * Регистрация
     */
    LOGIN(new LoginСommand(PathConstant.PATH_ROOT)),
    /**
     * Конец сеанса
     */
    LOGOUT(new LogoutСommand(PathConstant.PATH_ROOT)),
    /**
     * Смена локализации на RU
     */
    LOCALRU(new LocaleRuСommand(PathConstant.PATH_ROOT)),
    /**
     * Смена локализации на EN
     */
    LOCALEN(new LocaleEnСommand(PathConstant.PATH_ROOT)),
    /**
     * Профайл
     */
    PROFILE(new ProfileСommand(PathConstant.PATH_ROOT)),
//  admin
    /**
     * Главная страница администратора
     */
    ADMIN(new AdminCommand(PathConstant.PATH_ADMIN)),
    /**
     * Просмотр и удаление пользователей
     */
    EDITUSERS(new EditUsersCommand(PathConstant.PATH_ADMIN)),
    /**
     * Создание нового пользователя
     */
    CREATEUSER(new CreateUserCommand(PathConstant.PATH_ADMIN)),
    /**
     * Изменение пользователя
     */
    UPDATEUSER(new UpdateUserCommand(PathConstant.PATH_ADMIN)),
//cash
    /**
     * Главная страница кассира
     */
    MAIN(new MainCommand(PathConstant.PATH_CASH)),


    ;
    /**
     * Команда
     */
    public Сommand command;

    /**
     * Конструктор
     *
     * @param command - команда
     */
    Action(Сommand command) {
        this.command = command;
    }

    /**
     * Получение пути к странице
     *
     * @return путь
     */
    public String getJsp() {
        return command.getPath() + name().toLowerCase() + ".jsp";
    }

    /**
     * Определение действия
     *
     * @param request - запрос
     * @return действие
     */
    public static Action define(HttpServletRequest request) {
        try {
            String command = request.getParameter("command").toUpperCase();
            return Action.valueOf(command);
        } catch (Exception e) {
            Logger logger = LogManager.getLogger(EditUsersCommand.class);
            final HttpSession session = request.getSession(false);
            logger.log(Level.ERROR, e);
            session.setAttribute(AttributeConstant.ERROR, "108 ");
            return Action.INDEX;
        }
    }
}
