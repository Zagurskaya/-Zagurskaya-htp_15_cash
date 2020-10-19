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
public enum Action {
//  root
    /**
     * Стартовая страница
     */
    INDEX(new IndexСommand(PathConstant.PATH_INDEX)),
    /**
     * Ошибки
     */
    ERROR(new ErrorСommand(PathConstant.PATH_ERROR)),
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
//---------------ADMIN---------------------------------
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
//---------------CASH---------------------------------------
    /**
     * Главная страница кассира
     */
    MAIN(new MainCommand(PathConstant.PATH_CASH)),
    DUTIES(new DutiesCommand(PathConstant.PATH_CASH)),
    CURRENCY(new CurrencyCommand(PathConstant.PATH_CASH)),
//    OPERATION(new OperationCommand(PathConstant.PATH_CASH)),
//    REPORT(new ReportCommand(PathConstant.PATH_CASH)),

//------------------CASH / CURRENCY---------------------------
    ALLCURRENCY(new AllCurrencyCommand(PathConstant.PATH_CASH_CURRENCY)),
    RATECB(new RateCBCommand(PathConstant.PATH_CASH_CURRENCY)),
    RATENB(new RateNBCommand(PathConstant.PATH_CASH_CURRENCY)),

//------------------CASH / OPERATION--------------------------
    PAYMENT(new PaymentCommand(PathConstant.PATH_CASH_OPERATION)),
    BALANCE(new BalanceCommand(PathConstant.PATH_CASH_OPERATION)),
    USEROPERATIONS(new UserOperationsCommand(PathConstant.PATH_CASH_OPERATION)),

//-----------------CASH /OPERATION/PAYMENT---------------------
    SELECTPAYMENT(new SelectPaymentCommand(PathConstant.PATH_CASH_PAYMENT)),
    PAYMENT10_01(new Payment10_01_Command(PathConstant.PATH_CASH_PAYMENT)),
    PAYMENT10_02(new Payment10_02_Command(PathConstant.PATH_CASH_PAYMENT)),
    PAYMENT20_01(new Payment20_01_Command(PathConstant.PATH_CASH_PAYMENT)),
    PAYMENT20_02(new Payment20_02_Command(PathConstant.PATH_CASH_PAYMENT)),
    PAYMENT998(new Payment998_Command(PathConstant.PATH_CASH_PAYMENT)),
    PAYMENT1000(new Payment1000_Command(PathConstant.PATH_CASH_PAYMENT)),
    PAYMENT1100(new Payment1100_Command(PathConstant.PATH_CASH_PAYMENT)),
    PAYMENT1100BALANCE(new Payment1100BalanceCommand(PathConstant.PATH_CASH_PAYMENT)),
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
