package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.controller.command.impl.ErrorCommand;
import com.zagurskaya.cash.controller.command.impl.IndexCommand;
import com.zagurskaya.cash.controller.command.impl.LocaleCommand;
import com.zagurskaya.cash.controller.command.impl.LoginCommand;
import com.zagurskaya.cash.controller.command.impl.LogoutCommand;
import com.zagurskaya.cash.controller.command.impl.ProfileCommand;
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
 * Action list
 */
public enum ActionType {
//  root
    /**
     * Start page
     */
    INDEX(new IndexCommand(PathPage.PATH_INDEX)),
    /**
     * Error
     */
    ERROR(new ErrorCommand(PathPage.PATH_ERROR)),
    /**
     * Registration
     */
    LOGIN(new LoginCommand(PathPage.PATH_ROOT)),
    /**
     * End of session
     */
    LOGOUT(new LogoutCommand(PathPage.PATH_ROOT)),
    /**
     * Change locale
     */
    LOCALE(new LocaleCommand(PathPage.PATH_ROOT)),
    /**
     * Profile
     */
    PROFILE(new ProfileCommand(PathPage.PATH_ROOT)),
//---------------ADMIN---------------------------------
    /**
     * Admin home page
     */
    ADMIN(new AdminCommand(PathPage.PATH_ADMIN)),
    /**
     * View and delete users
     */
    EDIT_USERS(new EditUsersCommand(PathPage.PATH_ADMIN)),
    /**
     * Create a new user
     */
    CREATE_USER(new CreateUserCommand(PathPage.PATH_ADMIN)),
    /**
     * Update user
     */
    UPDATE_USER(new UpdateUserCommand(PathPage.PATH_ADMIN)),
//---------------CASH---------------------------------------
    /**
     * Cashier home page
     */
    MAIN(new MainCommand(PathPage.PATH_CASH)),
    /**
     * Cashier home page
     */
    DUTIES(new DutiesCommand(PathPage.PATH_CASH)),
    /**
     * Cashier home page
     */
    CURRENCY(new CurrencyCommand(PathPage.PATH_CASH)),
//    OPERATION(new OperationCommand(PathConstant.PATH_CASH)),
//    REPORT(new ReportCommand(PathConstant.PATH_CASH)),

    //------------------CASH / CURRENCY---------------------------
    /**
     * Cashier home page
     */
    ALL_CURRENCY(new AllCurrencyCommand(PathPage.PATH_CASH_CURRENCY)),
    /**
     * View rate CB
     */
    RATE_CB(new RateCBCommand(PathPage.PATH_CASH_CURRENCY)),
    /**
     * View rate NB
     */
    RATE_NB(new RateNBCommand(PathPage.PATH_CASH_CURRENCY)),

    //------------------CASH / OPERATION--------------------------
    /**
     * Payments
     */
    PAYMENT(new PaymentCommand(PathPage.PATH_CASH_OPERATION)),
    /**
     * Balance
     */
    BALANCE(new BalanceCommand(PathPage.PATH_CASH_OPERATION)),
    /**
     * User operations
     */
    USER_OPERATIONS(new UserOperationsCommand(PathPage.PATH_CASH_OPERATION)),

    //-----------------CASH /OPERATION/PAYMENT---------------------
    /**
     * Select payment
     */
    SELECT_PAYMENT(new SelectPaymentCommand(PathPage.PATH_CASH_PAYMENT)),
    /**
     * buy currency
     */
    PAYMENT10_01(new Payment10_01_Command(PathPage.PATH_CASH_PAYMENT)),
    /**
     * buy currency
     */
    PAYMENT10_02(new Payment10_02_Command(PathPage.PATH_CASH_PAYMENT)),
    /**
     * sale currency
     */
    PAYMENT20_01(new Payment20_01_Command(PathPage.PATH_CASH_PAYMENT)),
    /**
     * sale currency
     */
    PAYMENT20_02(new Payment20_02_Command(PathPage.PATH_CASH_PAYMENT)),
    /**
     * communal payment
     */
    PAYMENT998(new Payment998_Command(PathPage.PATH_CASH_PAYMENT)),
    /**
     * Receive money
     */
    PAYMENT1000(new Payment1000_Command(PathPage.PATH_CASH_PAYMENT)),
    /**
     * Return money
     */
    PAYMENT1100(new Payment1100_Command(PathPage.PATH_CASH_PAYMENT)),
    /**
     * payment balance
     */
    PAYMENT1100BALANCE(new Payment1100BalanceCommand(PathPage.PATH_CASH_PAYMENT)),
    ;
    /**
     * Command
     */
    private Command command;

    /**
     * Constructor
     *
     * @param command - command
     */
    ActionType(Command command) {
        this.command = command;
    }

    /**
     * Get field value command
     *
     * @return command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Get path to page
     *
     * @return path
     */
    public String getJsp() {
        return command.getDirectoryPath() + name().toLowerCase() + ".jsp";
    }

    /**
     * Defining an action
     *
     * @param request - request
     * @return action
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
