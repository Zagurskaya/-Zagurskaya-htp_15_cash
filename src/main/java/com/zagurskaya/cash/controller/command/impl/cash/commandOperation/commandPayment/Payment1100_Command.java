package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import com.zagurskaya.cash.constant.AttributeConstant;
import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.controller.command.impl.cash.commandOperation.PaymentCommand;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.SprOperation;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.DutiesService;
import com.zagurskaya.cash.model.service.PaymentService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import com.zagurskaya.cash.model.service.impl.DutiesServiceImpl;
import com.zagurskaya.cash.model.service.impl.PaymentServiceImpl;
import com.zagurskaya.cash.util.DataUtil;
import com.zagurskaya.cash.validation.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;


public class Payment1100_Command extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(Payment1100_Command.class);
    private final DutiesService dutiesService = new DutiesServiceImpl();
    private final CurrencyService currencyService = new CurrencyServiceImpl();

    /**
     * Конструктор
     *
     * @param path - путь
     */
    public Payment1100_Command(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
//        User user = Util.findUser(req);
//        LocalDate date = LocalDate.now();
//        Timestamp now = new Timestamp(System.currentTimeMillis());
//        String today = Util.getFormattedLocalDateStartDateTime(date);
////        "yyyy-MM-dd"
//        String todaySQL = Util.getFormattedLocalDateOnlyDate(date);
//        List<Duties> duties = new DutiesDao().OpenDutiesUserToday(user, today);
//        KassaDao kassaDao = new KassaDao();
//
//
//        CurrencyDao currencyDao = new CurrencyDao();
//        List<Currency> currencies = currencyDao.getAll();
//        req.setAttribute("currencies", currencies);
//
//        if (Form.isPost(req)) {
//            if (req.getParameter("enter") != null) {
//
//                long sprOperationsId = 1100;
//                SprOperationsDao sprOperationsDao = new SprOperationsDao();
//                List<SprOperations> sprOperations1000 = sprOperationsDao.getAll("WHERE `id`=" + sprOperationsId);
//
//                long[] ids = Form.getLongArray(req, "id");
//                double[] sums = Form.getDoubleArray(req, "sum");
//                String specification = Form.getString(req, "specification");
//
//
//                UserOperationDao userOperationDao = new UserOperationDao();
//                UserOperation userOperation = new UserOperation(0, now, 1, sums[0], ids[0], user.getId(), duties.get(0).getId(), sprOperationsId, specification, null, null);
//                userOperationDao.create(userOperation);
//
//
//                for (int i = 0; i < ids.length; i++) {
//
//                    SprEntriesDao sprEntriesDao = new SprEntriesDao();
//                    List<SprEntries> sprEntries1100 = sprEntriesDao.getAll("WHERE `sprOperationsId`=" + sprOperationsId + " AND `currencyId`=" + ids[i]);
//
//                    kassaDao.updateKassaOutSideOperation(Date.valueOf(todaySQL), duties.get(0).getId(), ids[i], sums[i], sprOperationsId);
//
//                    RateNBDao rateNBDao = new RateNBDao();
//                    UserEntryDao userEntryDao = new UserEntryDao();
//                    UserEntry userEntrys1100 = new UserEntry(0, userOperation.getId(), sprEntries1100.get(0).getId(), ids[i], sprEntries1100.get(0).getAccountDebit(), sprEntries1100.get(0).getAccountCredit(), sums[i], sprEntries1100.get(0).getIsSpending(), rateNBDao.rateNBToday(Date.valueOf(todaySQL), ids[i]));
//                    userEntryDao.create(userEntrys1100);
//
//                }
//
//                Action.CHECK1100.setPATH("/cash/operation/check/");
//                return Action.CHECK1100;
//
//            } else if (req.getParameter("balance") != null) {
//
//
//                Action.PAYMENT1100BALANCE.setPATH("/cash/operation/payment/");
//                return Action.PAYMENT1100BALANCE;
//
//            }
//        }
//        Action.PAYMENT1100.setPATH("/cash/operation/payment/");
//        return Action.PAYMENT1100;

        final HttpSession session = request.getSession(false);
        session.removeAttribute("error");
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        try {
            Action action = actionAfterValidationUserAndPermission(request, Action.PAYMENT1100);
            if (action == Action.PAYMENT1100) {
                User user = DataUtil.findUser(request);
                if (dutiesService.openDutiesUserToday(user, today) == null) {
                    return Action.DUTIES;
                }
                if (DataValidation.isCreateUpdateDeleteOperation(request)) {

                }

                List<Currency> currencies = currencyService.findAll();
                request.setAttribute(AttributeConstant.CURRENCIES, currencies);
                return Action.PAYMENT1100;
            } else {
                return action;
            }
        } catch (ServiceException | NumberFormatException e) {
            session.setAttribute(AttributeConstant.ERROR, "100 " + e);
            logger.log(Level.ERROR, e);
            return Action.ERROR;
        }
    }
}
