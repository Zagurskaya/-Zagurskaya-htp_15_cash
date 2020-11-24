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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public Long implementPayment1000(Map<Long, BigDecimal> map, String specification, User user) throws ServiceException {
        Long sprOperationId = 1000L;
        DutiesService dutiesService = new DutiesServiceImpl();
        KassaService kassaService = new KassaServiceImpl();
        KassaDao kassaDao = new KassaDaoImpl();
        UserOperationDao userOperationDao = new UserOperationDaoImpl();
        UserEntryDao userEntryDao = new UserEntryDaoImpl();
        SprEntryDao sprEntryDao = new SprEntryDaoImpl();
        RateNBDao rateNBDao = new RateNBDaoImpl();
        RateCBDao rateCBDao = new RateCBDaoImpl();

        LocalDateTime now = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);

        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userOperationDao, userEntryDao, sprEntryDao, kassaDao, rateNBDao, rateCBDao);
        try {
            Long firstKey = (Long) map.keySet().toArray()[0];
            BigDecimal valueForFirstKey = map.get(firstKey);
            Duties duties = dutiesService.openDutiesUserToday(user, today);
            BigDecimal rateCBPayment = rateCBDao.rateCBToday(now, firstKey, AttributeName.NС);
            UserOperation userOperation = new UserOperation.Builder()
                    .addLocalDateTime(now)
                    .addRate(rateCBPayment)
                    .addSum(valueForFirstKey)
                    .addCurrencyId(firstKey)
                    .addUserId(user.getId())
                    .addDutiesId(duties.getId())
                    .addOperationId(sprOperationId)
                    .addSpecification(specification)
                    .build();
            Long userOperationId = userOperationDao.create(userOperation);
            for (Map.Entry<Long, BigDecimal> entry : map.entrySet()) {
                Long currency = entry.getKey();
                BigDecimal sum = entry.getValue();
                List<SprEntry> sprEntries1000 = sprEntryDao.findAllBySprOperationIdAndCurrencyId(sprOperationId, currency);
                kassaService.updateKassaOuterOperation(date, duties.getId(), currency, sum, sprOperationId);
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
                            .addRate(rateNBDao.rateNBToday(date, currency).getSum())
                            .build();
                    userEntryDao.create(userEntry1000);
                }
            }
            transaction.commit();
            return userOperationId;
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
    public Long implementPayment1100(Map<Long, BigDecimal> map, String specification, User user) throws ServiceException {
        Long sprOperationId = 1100L;
        DutiesService dutiesService = new DutiesServiceImpl();
        KassaService kassaService = new KassaServiceImpl();
        KassaDao kassaDao = new KassaDaoImpl();
        UserOperationDao userOperationDao = new UserOperationDaoImpl();
        UserEntryDao userEntryDao = new UserEntryDaoImpl();
        SprEntryDao sprEntryDao = new SprEntryDaoImpl();
        RateNBDao rateNBDao = new RateNBDaoImpl();
        RateCBDao rateCBDao = new RateCBDaoImpl();

        LocalDateTime now = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);

        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userOperationDao, userEntryDao, sprEntryDao, kassaDao, rateNBDao, rateCBDao);
        try {
            Long firstKey = (Long) map.keySet().toArray()[0];
            BigDecimal valueForFirstKey = map.get(firstKey);
            Duties duties = dutiesService.openDutiesUserToday(user, today);
            BigDecimal rateCBPayment = rateCBDao.rateCBToday(now, firstKey, AttributeName.NС);
            UserOperation userOperation = new UserOperation.Builder()
                    .addLocalDateTime(now)
                    .addRate(rateCBPayment)
                    .addSum(valueForFirstKey)
                    .addCurrencyId(firstKey)
                    .addUserId(user.getId())
                    .addDutiesId(duties.getId())
                    .addOperationId(sprOperationId)
                    .addSpecification(specification)
                    .build();
            Long userOperationId = userOperationDao.create(userOperation);
            for (Map.Entry<Long, BigDecimal> entry : map.entrySet()) {
                Long currency = entry.getKey();
                BigDecimal sum = entry.getValue();
                List<SprEntry> sprEntries1100 = sprEntryDao.findAllBySprOperationIdAndCurrencyId(sprOperationId, currency);
                kassaService.updateKassaOuterOperation(date, duties.getId(), currency, sum, sprOperationId);
                for (SprEntry entryElement : sprEntries1100) {
                    UserEntry userEntry1000 = new UserEntry
                            .Builder()
                            .addUserOperationId(userOperationId)
                            .addSprEntryId(entryElement.getId())
                            .addCurrencyId(currency)
                            .addAccountDebit(entryElement.getAccountDebit())
                            .addAccountCredit(entryElement.getAccountCredit())
                            .addSum(sum)
                            .addIsSpending(entryElement.getIsSpending())
                            .addRate(rateNBDao.rateNBToday(date, currency).getSum())
                            .build();
                    userEntryDao.create(userEntry1000);
                }
            }
            transaction.commit();
            return userOperationId;
        } catch (DaoConstraintViolationException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Duplicate data duties ", e);
            throw new ServiceException("100 Duplicate data duties ", e);
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during implement Payment1100", e);
            throw new ServiceException("Database exception during implement Payment1100", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Long implementPayment10(Map<Long, BigDecimal> map, BigDecimal rate, String specification, User user) throws ServiceException {
        Long sprOperationId = 10L;
        DutiesService dutiesService = new DutiesServiceImpl();
        KassaService kassaService = new KassaServiceImpl();
        KassaDao kassaDao = new KassaDaoImpl();
        UserOperationDao userOperationDao = new UserOperationDaoImpl();
        UserEntryDao userEntryDao = new UserEntryDaoImpl();
        SprEntryDao sprEntryDao = new SprEntryDaoImpl();
        RateNBDao rateNBDao = new RateNBDaoImpl();
        RateCBDao rateCBDao = new RateCBDaoImpl();

        LocalDateTime now = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);

        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userOperationDao, userEntryDao, sprEntryDao, kassaDao, rateNBDao, rateCBDao);
        try {
            Long firstKey = (Long) map.keySet().toArray()[0];
            BigDecimal valueForFirstKey = map.get(firstKey);
            Duties duties = dutiesService.openDutiesUserToday(user, today);
            UserOperation userOperation = new UserOperation.Builder()
                    .addLocalDateTime(now)
                    .addRate(rate)
                    .addSum(valueForFirstKey)
                    .addCurrencyId(firstKey)
                    .addUserId(user.getId())
                    .addDutiesId(duties.getId())
                    .addOperationId(sprOperationId)
                    .addSpecification(specification)
                    .build();
            Long userOperationId = userOperationDao.create(userOperation);
            for (Map.Entry<Long, BigDecimal> entry : map.entrySet()) {
                Long currency = entry.getKey();
                BigDecimal sum = entry.getValue();
                List<SprEntry> sprEntries10 = sprEntryDao.findAllBySprOperationIdAndCurrencyId(sprOperationId, currency);
                kassaService.updateKassaInnerOperation(date, duties.getId(), currency, sum, sprOperationId);
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
                            .addRate(rateNBDao.rateNBToday(date, currency).getSum())
                            .build();
                    userEntryDao.create(userEntry10);
                }
            }
            transaction.commit();
            return userOperationId;
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
    public Long implementPayment20(Map<Long, BigDecimal> map, BigDecimal rate, String specification, User user) throws ServiceException {
        Long sprOperationId = 20L;
        DutiesService dutiesService = new DutiesServiceImpl();
        KassaService kassaService = new KassaServiceImpl();
        KassaDao kassaDao = new KassaDaoImpl();
        UserOperationDao userOperationDao = new UserOperationDaoImpl();
        UserEntryDao userEntryDao = new UserEntryDaoImpl();
        SprEntryDao sprEntryDao = new SprEntryDaoImpl();
        RateNBDao rateNBDao = new RateNBDaoImpl();
        RateCBDao rateCBDao = new RateCBDaoImpl();

        LocalDateTime now = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);

        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userOperationDao, userEntryDao, sprEntryDao, kassaDao, rateNBDao, rateCBDao);
        try {
            Long firstKey = (Long) map.keySet().toArray()[0];
            BigDecimal valueForFirstKey = map.get(firstKey);
            Duties duties = dutiesService.openDutiesUserToday(user, today);
            UserOperation userOperation = new UserOperation.Builder()
                    .addLocalDateTime(now)
                    .addRate(rate)
                    .addSum(valueForFirstKey)
                    .addCurrencyId(firstKey)
                    .addUserId(user.getId())
                    .addDutiesId(duties.getId())
                    .addOperationId(sprOperationId)
                    .addSpecification(specification)
                    .build();
            Long userOperationId = userOperationDao.create(userOperation);
            for (Map.Entry<Long, BigDecimal> entry : map.entrySet()) {
                Long currency = entry.getKey();
                BigDecimal sum = entry.getValue();
                List<SprEntry> sprEntries20 = sprEntryDao.findAllBySprOperationIdAndCurrencyId(sprOperationId, currency);
                kassaService.updateKassaInnerOperation(date, duties.getId(), currency, sum, sprOperationId);
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
                            .addRate(rateNBDao.rateNBToday(date, currency).getSum())
                            .build();
                    userEntryDao.create(userEntry10);
                }
            }
            transaction.commit();
            return userOperationId;
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
    public Long implementPayment998(Map<Long, BigDecimal> map, String specification, String checkingAccount, String fullName, User user) throws ServiceException {
        Long sprOperationId = 998L;
        DutiesService dutiesService = new DutiesServiceImpl();
        KassaService kassaService = new KassaServiceImpl();
        KassaDao kassaDao = new KassaDaoImpl();
        UserOperationDao userOperationDao = new UserOperationDaoImpl();
        UserEntryDao userEntryDao = new UserEntryDaoImpl();
        SprEntryDao sprEntryDao = new SprEntryDaoImpl();
        RateNBDao rateNBDao = new RateNBDaoImpl();
        RateCBDao rateCBDao = new RateCBDaoImpl();

        LocalDateTime now = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);

        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userOperationDao, userEntryDao, sprEntryDao, kassaDao, rateNBDao, rateCBDao);
        try {
            Long firstKey = (Long) map.keySet().toArray()[0];
            BigDecimal valueForFirstKey = map.get(firstKey);
            Duties duties = dutiesService.openDutiesUserToday(user, today);
            BigDecimal rateCBPayment = !AttributeName.NС.equals(firstKey) ? rateCBDao.rateCBToday(now, firstKey, AttributeName.NС) : new BigDecimal(1);
            UserOperation userOperation = new UserOperation.Builder()
                    .addLocalDateTime(now)
                    .addRate(rateCBPayment)
                    .addSum(valueForFirstKey)
                    .addCurrencyId(firstKey)
                    .addUserId(user.getId())
                    .addDutiesId(duties.getId())
                    .addOperationId(sprOperationId)
                    .addSpecification(specification)
                    .addCheckingAccount(checkingAccount)
                    .addFullName(fullName)
                    .build();
            Long userOperationId = userOperationDao.create(userOperation);
            for (Map.Entry<Long, BigDecimal> entry : map.entrySet()) {
                Long currency = entry.getKey();
                BigDecimal sum = entry.getValue();
                List<SprEntry> sprEntries998 = sprEntryDao.findAllBySprOperationIdAndCurrencyId(sprOperationId, currency);
                kassaService.updateKassaInnerOperation(date, duties.getId(), currency, sum, sprOperationId);
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
                            .addRate(rateNBDao.rateNBToday(date, currency).getSum())
                            .build();
                    userEntryDao.create(userEntry998);
                }
            }
            transaction.commit();
            return userOperationId;
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

    @Override
    public UserOperation findUserOperationById(Long id) throws ServiceException {
        UserOperationDao userOperationDao = new UserOperationDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userOperationDao);
        try {
            return userOperationDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during find UserOperation By Id", e);
            throw new ServiceException("Database exception during find UserOperation By Id", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<UserEntry> findAllUserEntriesByOperationId(Long id) throws ServiceException {
        UserEntryDao userEntryDao = new UserEntryDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userEntryDao);
        try {
            return userEntryDao.findUserEntriesByOperationId(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend all UserEntry by operation id", e);
            throw new ServiceException("Database exception during fiend all  UserEntry by operation id", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

}
