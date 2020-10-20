package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.controller.command.impl.ErrorСommand;
import com.zagurskaya.cash.controller.command.impl.IndexСommand;
import com.zagurskaya.cash.controller.command.impl.LocaleEnСommand;
import com.zagurskaya.cash.controller.command.impl.LocaleRuСommand;
import com.zagurskaya.cash.controller.command.impl.LoginСommand;
import com.zagurskaya.cash.controller.command.impl.LogoutСommand;
import com.zagurskaya.cash.controller.command.impl.ProfileСommand;
import com.zagurskaya.cash.controller.command.impl.admin.AdminCommand;
import com.zagurskaya.cash.controller.command.impl.admin.CreateUserCommand;
import com.zagurskaya.cash.controller.command.impl.admin.EditUsersCommand;
import com.zagurskaya.cash.controller.command.impl.admin.UpdateUserCommand;
import com.zagurskaya.cash.controller.command.impl.cash.CurrencyCommand;
import com.zagurskaya.cash.controller.command.impl.cash.DutiesCommand;
import com.zagurskaya.cash.controller.command.impl.cash.MainCommand;
import com.zagurskaya.cash.controller.command.impl.cash.commandCurrency.AllCurrencyCommand;
import com.zagurskaya.cash.controller.command.impl.cash.commandCurrency.RateCBCommand;
import com.zagurskaya.cash.controller.command.impl.cash.commandCurrency.RateNBCommand;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.BalanceCommand;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.PaymentCommand;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.UserOperationsCommand;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment.Payment1000_Command;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment.Payment10_01_Command;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment.Payment10_02_Command;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment.Payment1100BalanceCommand;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment.Payment1100_Command;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment.Payment20_01_Command;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment.Payment20_02_Command;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment.Payment998_Command;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment.SelectPaymentCommand;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Перечень действий
 */
public enum ActionType {
//  root
    /**
     * Стартовая страница
     */
    INDEX(new IndexСommand(PathPage.PATH_INDEX)),
    /**
     * Ошибки
     */
    ERROR(new ErrorСommand(PathPage.PATH_ERROR)),
    /**
     * Регистрация
     */
    LOGIN(new LoginСommand(PathPage.PATH_ROOT)),
    /**
     * Конец сеанса
     */
    LOGOUT(new LogoutСommand(PathPage.PATH_ROOT)),
    /**
     * Смена локализации на RU
     */
    LOCALRU(new LocaleRuСommand(PathPage.PATH_ROOT)),
    /**
     * Смена локализации на EN
     */
    LOCALEN(new LocaleEnСommand(PathPage.PATH_ROOT)),
    /**
     * Профайл
     */
    PROFILE(new ProfileСommand(PathPage.PATH_ROOT)),
//---------------ADMIN---------------------------------
    /**
     * Главная страница администратора
     */
    ADMIN(new AdminCommand(PathPage.PATH_ADMIN)),
    /**
     * Просмотр и удаление пользователей
     */
    EDITUSERS(new EditUsersCommand(PathPage.PATH_ADMIN)),
    /**
     * Создание нового пользователя
     */
    CREATEUSER(new CreateUserCommand(PathPage.PATH_ADMIN)),
    /**
     * Изменение пользователя
     */
    UPDATEUSER(new UpdateUserCommand(PathPage.PATH_ADMIN)),
//---------------CASH---------------------------------------
    /**
     * Главная страница кассира
     */
    MAIN(new MainCommand(PathPage.PATH_CASH)),
    DUTIES(new DutiesCommand(PathPage.PATH_CASH)),
    CURRENCY(new CurrencyCommand(PathPage.PATH_CASH)),
//    OPERATION(new OperationCommand(PathConstant.PATH_CASH)),
//    REPORT(new ReportCommand(PathConstant.PATH_CASH)),

    //------------------CASH / CURRENCY---------------------------
    ALLCURRENCY(new AllCurrencyCommand(PathPage.PATH_CASH_CURRENCY)),
    RATECB(new RateCBCommand(PathPage.PATH_CASH_CURRENCY)),
    RATENB(new RateNBCommand(PathPage.PATH_CASH_CURRENCY)),

    //------------------CASH / OPERATION--------------------------
    PAYMENT(new PaymentCommand(PathPage.PATH_CASH_OPERATION)),
    BALANCE(new BalanceCommand(PathPage.PATH_CASH_OPERATION)),
    USEROPERATIONS(new UserOperationsCommand(PathPage.PATH_CASH_OPERATION)),

    //-----------------CASH /OPERATION/PAYMENT---------------------
    SELECTPAYMENT(new SelectPaymentCommand(PathPage.PATH_CASH_PAYMENT)),
    PAYMENT10_01(new Payment10_01_Command(PathPage.PATH_CASH_PAYMENT)),
    PAYMENT10_02(new Payment10_02_Command(PathPage.PATH_CASH_PAYMENT)),
    PAYMENT20_01(new Payment20_01_Command(PathPage.PATH_CASH_PAYMENT)),
    PAYMENT20_02(new Payment20_02_Command(PathPage.PATH_CASH_PAYMENT)),
    PAYMENT998(new Payment998_Command(PathPage.PATH_CASH_PAYMENT)),
    PAYMENT1000(new Payment1000_Command(PathPage.PATH_CASH_PAYMENT)),
    PAYMENT1100(new Payment1100_Command(PathPage.PATH_CASH_PAYMENT)),
    PAYMENT1100BALANCE(new Payment1100BalanceCommand(PathPage.PATH_CASH_PAYMENT)),
    ;
    /**
     * Команда
     */
    private Сommand command;

    /**
     * Конструктор
     *
     * @param command - команда
     */
    ActionType(Сommand command) {
        this.command = command;
    }

    /**
     * Получение значения поля command
     *
     * @return команда
     */
    public Сommand getCommand() {
        return command;
    }

    /**
     * Получение пути к странице
     *
     * @return путь
     */
    public String getJsp() {
        return command.getDirectoryPath() + name().toLowerCase() + ".jsp";
    }

    /**
     * Определение действия
     *
     * @param request - запрос
     * @return действие
     */
    public static ActionType define(HttpServletRequest request) {
        try {
            String command = request.getParameter("command").toUpperCase();
            return ActionType.valueOf(command);
        } catch (Exception e) {
            Logger logger = LogManager.getLogger(EditUsersCommand.class);
            final HttpSession session = request.getSession(false);
            logger.log(Level.ERROR, e);
            session.setAttribute(AttributeName.ERROR, "108 ");
            return ActionType.INDEX;
        }
    }
}
