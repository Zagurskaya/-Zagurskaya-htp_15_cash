package com.zagurskaya.cash.model.service.impl;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.Kassa;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.dao.CurrencyDao;
import com.zagurskaya.cash.model.dao.DutiesDao;
import com.zagurskaya.cash.model.dao.KassaDao;
import com.zagurskaya.cash.model.dao.impl.CurrencyDaoImpl;
import com.zagurskaya.cash.model.dao.impl.DutiesDaoImpl;
import com.zagurskaya.cash.model.dao.impl.KassaDaoImpl;
import com.zagurskaya.cash.model.service.EntityTransaction;
import com.zagurskaya.cash.model.service.DutiesService;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DutiesServiceImpl implements DutiesService {

    private static final Logger logger = LogManager.getLogger(DutiesServiceImpl.class);

    @Override
    public Duties findById(Long id) throws ServiceException {
        DutiesDao dutiesDao = new DutiesDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dutiesDao);
        try {
            return dutiesDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend duties by id", e);
            throw new ServiceException("Database exception during fiend duties by id", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean create(Duties duties) throws ServiceException, CommandException {
        DutiesDao dutiesDao = new DutiesDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dutiesDao);
        try {
            return dutiesDao.create(duties) != 0L;
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data duties ", e);
            throw new CommandException("100 Duplicate data duties ", e);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during createCheckEn duties ", e);
            throw new ServiceException("Database exception during createCheckEn duties ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean update(Duties duties) throws ServiceException, CommandException {
        DutiesDao dutiesDao = new DutiesDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dutiesDao);
        try {
            return dutiesDao.update(duties);
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data duties ", e);
            throw new CommandException("100 Duplicate data duties ", e);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during update duties ", e);
            throw new ServiceException(" Database exception during update duties ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean delete(Duties duties) throws ServiceException {
        DutiesDao dutiesDao = new DutiesDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dutiesDao);
        try {
            return dutiesDao.delete(duties);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during delete duties ", e);
            throw new ServiceException("Database exception during delete duties ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public int countRows() throws ServiceException {
        DutiesDao dutiesDao = new DutiesDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dutiesDao);
        try {
            return dutiesDao.countRows();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend count dutiesList row", e);
            throw new ServiceException("Database exception during fiend count dutiesList row", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<Duties> onePartOfListOnPage(int page) throws ServiceException {
        List dutiesList = new ArrayList();
        DutiesDao dutiesDao = new DutiesDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dutiesDao);
        try {
            int recordsPerPage = AttributeName.RECORDS_PER_PAGE;
            int startRecord = (int) Math.ceil((page - 1) * recordsPerPage);
            dutiesList.addAll(dutiesDao.findAll(recordsPerPage, startRecord));
            return dutiesList;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend all duties", e);
            throw new ServiceException(" Database exception during fiend all duties", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public Duties openDutiesUserToday(User user, String today) throws ServiceException {
        List<Duties> dutiesList = new ArrayList<>();
        DutiesDao dutiesDao = new DutiesDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dutiesDao);
        try {
            dutiesList.addAll(dutiesDao.openDutiesUserToday(user.getId(), today));
            if (dutiesList.size() > 1) {
                logger.log(Level.ERROR, "user " + user.getLogin() + " has more than one open duty");
                throw new ServiceException("user " + user.getLogin() + " has more than one open duty");
            }
            return dutiesList.stream().findFirst().orElse(null);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend all duties", e);
            throw new ServiceException("Database exception during fiend all duties", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public void openNewDuties(User user) throws ServiceException {
        DutiesDao dutiesDao = new DutiesDaoImpl();
        CurrencyDao currencyDao = new CurrencyDaoImpl();
        KassaDao kassaDao = new KassaDaoImpl();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);

        EntityTransaction transaction = new EntityTransaction();
        transaction.init(dutiesDao, currencyDao, kassaDao);
        try {
            Integer numberDuties = dutiesDao.numberDutiesToday(user, today);
            Duties duties = new Duties.Builder()
                    .addUserId(user.getId())
                    .addTimestamp(now)
                    .addNumber(numberDuties)
                    .addIsClose(false)
                    .build();
            Long dutiesId = dutiesDao.create(duties);
            List<Currency> currencies = currencyDao.findAll();
            for (Currency currencyElement : currencies) {
                Kassa newKassa = new Kassa.Builder()
                        .addCurrencyId(currencyElement.getId())
                        .addReceived(0.00)
                        .addComing(0.00)
                        .addSpending(0.00)
                        .addTransmitted(0.00)
                        .addBalance(0.00)
                        .addUserId(user.getId())
                        .addData(date)
                        .addDutiesId(dutiesId)
                        .build();
                kassaDao.create(newKassa);
            }
            transaction.commit();
        } catch (DaoConstraintViolationException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Duplicate data duties ", e);
            throw new ServiceException("100 Duplicate data duties ", e);
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during fiend duties by id", e);
            throw new ServiceException("Database exception during fiend duties by id", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void closeOpenDutiesUserToday(User user) throws ServiceException {
        DutiesDao dutiesDao = new DutiesDaoImpl();
        LocalDate date = LocalDate.now();
        String today = DataUtil.getFormattedLocalDateStartDateTime(date);
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dutiesDao);
        try {
            List<Duties> openDutiesList = dutiesDao.openDutiesUserToday(user.getId(), today);
            if (openDutiesList.size() == 1) {
                Duties openDuties = openDutiesList.get(0);
                Duties closeDuties = new Duties.Builder()
                        .addId(openDuties.getId())
                        .addUserId(openDuties.getUserId())
                        .addTimestamp(openDuties.getTimestamp())
                        .addNumber(openDuties.getNumber())
                        .addIsClose(true)
                        .build();
                dutiesDao.update(closeDuties);
            } else if (openDutiesList.size() == 0) {
                logger.log(Level.ERROR, "User has not open duties " + user.getLogin());
                throw new ServiceException("202 " + user.getLogin());
            } else {
                openDutiesList.size();
                logger.log(Level.ERROR, "User has more that one open duties " + user.getLogin());
                throw new ServiceException("203 " + user.getLogin());
            }
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data duties ", e);
            throw new ServiceException("100 Duplicate data duties ", e);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during update duties ", e);
            throw new ServiceException("Database exception during update duties ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }
}
