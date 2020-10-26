package com.zagurskaya.cash.model.service.impl;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.SprEntry;
import com.zagurskaya.cash.entity.SprOperation;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.dao.KassaDao;
import com.zagurskaya.cash.model.dao.RateCBDao;
import com.zagurskaya.cash.model.dao.RateNBDao;
import com.zagurskaya.cash.model.dao.SprEntryDao;
import com.zagurskaya.cash.model.dao.SprOperationDao;
import com.zagurskaya.cash.model.dao.UserEntryDao;
import com.zagurskaya.cash.model.dao.UserOperationDao;
import com.zagurskaya.cash.model.dao.impl.KassaDaoImpl;
import com.zagurskaya.cash.model.dao.impl.RateCBDaoImpl;
import com.zagurskaya.cash.model.dao.impl.RateNBDaoImpl;
import com.zagurskaya.cash.model.dao.impl.SprEntryDaoImpl;
import com.zagurskaya.cash.model.dao.impl.SprOperationDaoImpl;
import com.zagurskaya.cash.model.dao.impl.UserEntryDaoImpl;
import com.zagurskaya.cash.model.dao.impl.UserOperationDaoImpl;
import com.zagurskaya.cash.model.service.DutiesService;
import com.zagurskaya.cash.model.service.EntityTransaction;
import com.zagurskaya.cash.model.service.KassaService;
import com.zagurskaya.cash.model.service.PaymentService;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LogManager.getLogger(PaymentServiceImpl.class);

    @Override
    public List<SprOperation> findAllSprOperation() throws ServiceException {
        SprOperationDao sprOperationDao = new SprOperationDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(sprOperationDao);
        try {
            return sprOperationDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend all sprOperation", e);
            throw new ServiceException("Database exception during fiend all sprOperation", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public SprOperation findSprOperationById(Long id) throws ServiceException {
        SprOperationDao sprOperationDao = new SprOperationDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(sprOperationDao);
        try {
            return sprOperationDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during find SprOperation By Id", e);
            throw new ServiceException("Database exception during find SprOperation By Id", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public void implementPayment1000(Map<Long, Double> map, String specification, User user) throws ServiceException {
        Long sprOperationId = 1000L;
        DutiesService dutiesService = new DutiesServiceImpl();
        KassaService kassaService = new KassaServiceImpl();
        KassaDao kassaDao = new KassaDaoImpl();
        UserOperationDao userOperationDao = new UserOperationDaoImpl();
        UserEntryDao userEntryDao = new UserEntryDaoImpl();
        SprEntryDao sprEntryDao = new SprEntryDaoImpl();
        RateNBDao rateNBDao = new RateNBDaoImpl();
        RateCBDao rateCBDao = new RateCBDaoImpl();

        Timestamp now = new Timestamp(System.currentTimeMillis());
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        String todaySQL = DataUtil.getFormattedLocalDateOnlyDate(date);

        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userOperationDao, userEntryDao, sprEntryDao, kassaDao, rateNBDao, rateCBDao);
        try {
            Long firstKey = (Long) map.keySet().toArray()[0];
            Double valueForFirstKey = map.get(firstKey);
            Duties duties = dutiesService.openDutiesUserToday(user, today);
            double rateCBPayment = rateCBDao.rateCBToday(now, firstKey, 933L);
            UserOperation userOperation = new UserOperation.Builder()
                    .addTimestamp(now)
                    .addRate(rateCBPayment)
                    .addSum(valueForFirstKey)
                    .addCurrencyId(firstKey)
                    .addUserId(user.getId())
                    .addDutiesId(duties.getId())
                    .addOperationId(sprOperationId)
                    .addSpecification(specification)
                    .build();
            Long userOperationId = userOperationDao.create(userOperation);
            for (Map.Entry<Long, Double> entry : map.entrySet()) {
                Long currency = entry.getKey();
                Double sum = entry.getValue();
                List<SprEntry> sprEntries1000 = sprEntryDao.findAllBySprOperationIdAndCurrencyId(sprOperationId, currency);
                kassaService.updateKassaOuterOperation(Date.valueOf(todaySQL), duties.getId(), currency, sum, sprOperationId);
                for (SprEntry entryElement : sprEntries1000) {
                    UserEntry userEntry1000 = new UserEntry
                            .Builder()
                            .addUserOperationId(userOperationId)
                            .addSprEntryId(entryElement.getId())
                            .addCurrencyId(currency)
                            .addAccountDebit(entryElement.getAccountDebit())
                            .addAccountCredit(entryElement.getAccountCredit())
                            .addSum(sum)
                            .addIsSpending(entryElement.getIsSpending())
                            .addRate(rateNBDao.rateNBToday(Date.valueOf(todaySQL), currency).getSum())
                            .build();
                    userEntryDao.create(userEntry1000);
                }
            }
            transaction.commit();
        } catch (DaoConstraintViolationException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Duplicate data duties ", e);
            throw new ServiceException("100 Duplicate data duties ", e);
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during implement Payment1000", e);
            throw new ServiceException("Database exception during implement Payment1000", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void implementPayment1100(Map<Long, Double> map, String specification, User user) throws ServiceException {

    }

    @Override
    public void implementPayment10(Map<Long, Double> map, Double rate, String specification, User user) throws ServiceException {
        Long sprOperationId = 10L;
        DutiesService dutiesService = new DutiesServiceImpl();
        KassaService kassaService = new KassaServiceImpl();
        KassaDao kassaDao = new KassaDaoImpl();
        UserOperationDao userOperationDao = new UserOperationDaoImpl();
        UserEntryDao userEntryDao = new UserEntryDaoImpl();
        SprEntryDao sprEntryDao = new SprEntryDaoImpl();
        RateNBDao rateNBDao = new RateNBDaoImpl();
        RateCBDao rateCBDao = new RateCBDaoImpl();

        Timestamp now = new Timestamp(System.currentTimeMillis());
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        String todaySQL = DataUtil.getFormattedLocalDateOnlyDate(date);

        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userOperationDao, userEntryDao, sprEntryDao, kassaDao, rateNBDao, rateCBDao);
        try {
            Long firstKey = (Long) map.keySet().toArray()[0];
            Double valueForFirstKey = map.get(firstKey);
            Duties duties = dutiesService.openDutiesUserToday(user, today);
            UserOperation userOperation = new UserOperation.Builder()
                    .addTimestamp(now)
                    .addRate(rate)
                    .addSum(valueForFirstKey)
                    .addCurrencyId(firstKey)
                    .addUserId(user.getId())
                    .addDutiesId(duties.getId())
                    .addOperationId(sprOperationId)
                    .addSpecification(specification)
                    .build();
            Long userOperationId = userOperationDao.create(userOperation);
            for (Map.Entry<Long, Double> entry : map.entrySet()) {
                Long currency = entry.getKey();
                Double sum = entry.getValue();
                List<SprEntry> sprEntries10 = sprEntryDao.findAllBySprOperationIdAndCurrencyId(sprOperationId, currency);
                kassaService.updateKassaInnerOperation(Date.valueOf(todaySQL), duties.getId(), currency, sum, sprOperationId);
                for (SprEntry entryElement : sprEntries10) {
                    UserEntry userEntry10 = new UserEntry
                            .Builder()
                            .addUserOperationId(userOperationId)
                            .addSprEntryId(entryElement.getId())
                            .addCurrencyId(currency)
                            .addAccountDebit(entryElement.getAccountDebit())
                            .addAccountCredit(entryElement.getAccountCredit())
                            .addSum(sum)
                            .addIsSpending(entryElement.getIsSpending())
                            .addRate(rateNBDao.rateNBToday(Date.valueOf(todaySQL), currency).getSum())
                            .build();
                    userEntryDao.create(userEntry10);
                }
            }
            transaction.commit();
        } catch (DaoConstraintViolationException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Duplicate data duties ", e);
            throw new ServiceException("100 Duplicate data duties ", e);
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during implement Payment10", e);
            throw new ServiceException("Database exception during implement Payment10", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void implementPayment20(Map<Long, Double> map, Double rate, String specification, User user) throws ServiceException {
        Long sprOperationId = 20L;
        DutiesService dutiesService = new DutiesServiceImpl();
        KassaService kassaService = new KassaServiceImpl();
        KassaDao kassaDao = new KassaDaoImpl();
        UserOperationDao userOperationDao = new UserOperationDaoImpl();
        UserEntryDao userEntryDao = new UserEntryDaoImpl();
        SprEntryDao sprEntryDao = new SprEntryDaoImpl();
        RateNBDao rateNBDao = new RateNBDaoImpl();
        RateCBDao rateCBDao = new RateCBDaoImpl();

        Timestamp now = new Timestamp(System.currentTimeMillis());
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        String todaySQL = DataUtil.getFormattedLocalDateOnlyDate(date);

        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userOperationDao, userEntryDao, sprEntryDao, kassaDao, rateNBDao, rateCBDao);
        try {
            Long firstKey = (Long) map.keySet().toArray()[0];
            Double valueForFirstKey = map.get(firstKey);
            Duties duties = dutiesService.openDutiesUserToday(user, today);
            UserOperation userOperation = new UserOperation.Builder()
                    .addTimestamp(now)
                    .addRate(rate)
                    .addSum(valueForFirstKey)
                    .addCurrencyId(firstKey)
                    .addUserId(user.getId())
                    .addDutiesId(duties.getId())
                    .addOperationId(sprOperationId)
                    .addSpecification(specification)
                    .build();
            Long userOperationId = userOperationDao.create(userOperation);
            for (Map.Entry<Long, Double> entry : map.entrySet()) {
                Long currency = entry.getKey();
                Double sum = entry.getValue();
                List<SprEntry> sprEntries20 = sprEntryDao.findAllBySprOperationIdAndCurrencyId(sprOperationId, currency);
                kassaService.updateKassaInnerOperation(Date.valueOf(todaySQL), duties.getId(), currency, sum, sprOperationId);
                for (SprEntry entryElement : sprEntries20) {
                    UserEntry userEntry10 = new UserEntry
                            .Builder()
                            .addUserOperationId(userOperationId)
                            .addSprEntryId(entryElement.getId())
                            .addCurrencyId(currency)
                            .addAccountDebit(entryElement.getAccountDebit())
                            .addAccountCredit(entryElement.getAccountCredit())
                            .addSum(sum)
                            .addIsSpending(entryElement.getIsSpending())
                            .addRate(rateNBDao.rateNBToday(Date.valueOf(todaySQL), currency).getSum())
                            .build();
                    userEntryDao.create(userEntry10);
                }
            }
            transaction.commit();
        } catch (DaoConstraintViolationException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Duplicate data duties ", e);
            throw new ServiceException("100 Duplicate data duties ", e);
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during implement Payment20", e);
            throw new ServiceException("Database exception during implement Payment20", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void implementPayment998(Map<Long, Double> map, String specification, String checkingAccount, String fullName, User user) throws ServiceException {
        Long sprOperationId = 998L;
        DutiesService dutiesService = new DutiesServiceImpl();
        KassaService kassaService = new KassaServiceImpl();
        KassaDao kassaDao = new KassaDaoImpl();
        UserOperationDao userOperationDao = new UserOperationDaoImpl();
        UserEntryDao userEntryDao = new UserEntryDaoImpl();
        SprEntryDao sprEntryDao = new SprEntryDaoImpl();
        RateNBDao rateNBDao = new RateNBDaoImpl();
        RateCBDao rateCBDao = new RateCBDaoImpl();

        Timestamp now = new Timestamp(System.currentTimeMillis());
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        String todaySQL = DataUtil.getFormattedLocalDateOnlyDate(date);

        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userOperationDao, userEntryDao, sprEntryDao, kassaDao, rateNBDao, rateCBDao);
        try {
            Long firstKey = (Long) map.keySet().toArray()[0];
            Double valueForFirstKey = map.get(firstKey);
            Duties duties = dutiesService.openDutiesUserToday(user, today);
            double rateCBPayment = firstKey != 933L ? rateCBDao.rateCBToday(now, firstKey, 933L) : 1;
            UserOperation userOperation = new UserOperation.Builder()
                    .addTimestamp(now)
                    .addRate(rateCBPayment)
                    .addSum(valueForFirstKey)
                    .addCurrencyId(firstKey)
                    .addUserId(user.getId())
                    .addDutiesId(duties.getId())
                    .addOperationId(sprOperationId)
                    .addSpecification(specification)
                    .build();
            Long userOperationId = userOperationDao.create(userOperation);
            for (Map.Entry<Long, Double> entry : map.entrySet()) {
                Long currency = entry.getKey();
                Double sum = entry.getValue();
                List<SprEntry> sprEntries998 = sprEntryDao.findAllBySprOperationIdAndCurrencyId(sprOperationId, currency);
                kassaService.updateKassaInnerOperation(Date.valueOf(todaySQL), duties.getId(), currency, sum, sprOperationId);
                for (SprEntry entryElement : sprEntries998) {
                    UserEntry userEntry998 = new UserEntry
                            .Builder()
                            .addUserOperationId(userOperationId)
                            .addSprEntryId(entryElement.getId())
                            .addCurrencyId(currency)
                            .addAccountDebit(entryElement.getAccountDebit())
                            .addAccountCredit(entryElement.getAccountCredit())
                            .addSum(sum)
                            .addIsSpending(entryElement.getIsSpending())
                            .addRate(rateNBDao.rateNBToday(Date.valueOf(todaySQL), currency).getSum())
                            .build();
                    userEntryDao.create(userEntry998);
                }
            }
            transaction.commit();
        } catch (DaoConstraintViolationException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Duplicate data duties ", e);
            throw new ServiceException("100 Duplicate data duties ", e);
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during implement Payment998", e);
            throw new ServiceException("Database exception during implement Payment998", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public int countRowsUserOperations(User user, Duties duties) throws ServiceException {
        UserOperationDao userOperationDao = new UserOperationDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userOperationDao);
        try {
            List<UserOperation> userOperations = userOperationDao.findAllByUserIdAndDutiesId(user.getId(), duties.getId(), 0, 0);
            return userOperations.size();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during count rows user operations", e);
            throw new ServiceException("Database exception during count rows user operations", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<UserOperation> onePartOfListUserOperationsOnPage(User user, Duties duties, int page) throws
            ServiceException {
        UserOperationDao userOperationDao = new UserOperationDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userOperationDao);
        try {
            int recordsPerPage = AttributeName.RECORDS_PER_PAGE;
            int startRecord = (int) Math.ceil((page - 1) * recordsPerPage);
            return userOperationDao.findAllByUserIdAndDutiesId(user.getId(), duties.getId(), recordsPerPage, startRecord);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during method onePartOfListUserOperationsOnPage", e);
            throw new ServiceException("Database exception during method onePartOfListUserOperationsOnPage", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

}
