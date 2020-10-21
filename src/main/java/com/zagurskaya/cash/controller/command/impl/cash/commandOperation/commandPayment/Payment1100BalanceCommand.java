package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.ActionType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Payment1100BalanceCommand extends AbstractСommand {
    /**
     * Конструктор
     *
     * @param directoryPath - путь
     */
    public Payment1100BalanceCommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest req, HttpServletResponse response) {
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
//        List<Kassa> balanceList = kassaDao.getAll("WHERE `userId`=" + user.getId() + " AND `dutiesId`=" + duties.get(duties.size()-1).getId());
//        req.setAttribute("balanceList", balanceList);
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
//            }
//        }
//        Action.PAYMENT1100BALANCE.setPATH("/cash/operation/payment/");
        return ActionType.PAYMENT1100BALANCE;
    }
}
