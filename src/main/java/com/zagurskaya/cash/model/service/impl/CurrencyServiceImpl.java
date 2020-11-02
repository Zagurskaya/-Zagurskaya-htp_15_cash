package com.zagurskaya.cash.model.service.impl;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.dao.CurrencyDao;
import com.zagurskaya.cash.model.dao.impl.CurrencyDaoImpl;
import com.zagurskaya.cash.model.service.EntityTransaction;
import com.zagurskaya.cash.model.service.CurrencyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger logger = LogManager.getLogger(CurrencyServiceImpl.class);

    @Override
    public Currency findById(Long id) throws ServiceException {
        CurrencyDao currencyDao = new CurrencyDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(currencyDao);
        try {
            return currencyDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend currency by id", e);
            throw new ServiceException("Database exception during fiend currency by id", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean create(Currency currency) throws ServiceException, CommandException {
        CurrencyDao currencyDao = new CurrencyDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(currencyDao);
        try {
            return currencyDao.create(currency) != 0L;
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data currency ", e);
            throw new CommandException("Duplicate data currency ", e);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during createCheckEn currency ", e);
            throw new ServiceException("Database exception during createCheckEn currency ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean update(Currency currency) throws ServiceException, CommandException {
        CurrencyDao currencyDao = new CurrencyDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(currencyDao);
        try {
            return currencyDao.update(currency);
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data currency ", e);
            throw new CommandException("Duplicate data currency ", e);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during update currency ", e);
            throw new ServiceException("Database exception during update currency ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean delete(Currency currency) throws ServiceException {
        CurrencyDao currencyDao = new CurrencyDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(currencyDao);
        try {
            return currencyDao.delete(currency);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during delete currency ", e);
            throw new ServiceException("Database exception during delete currency ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public int countRows() throws ServiceException {
        CurrencyDao currencyDao = new CurrencyDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(currencyDao);
        try {
            return currencyDao.countRows();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend count currencies row", e);
            throw new ServiceException("Database exception during fiend count currencies row", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<Currency> onePartOfListOnPage(int page) throws ServiceException {
        List currencies = new ArrayList();
        CurrencyDao currencyDao = new CurrencyDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(currencyDao);
        try {
            int recordsPerPage = AttributeName.RECORDS_PER_PAGE;
            int startRecord = (int) Math.ceil((page - 1) * recordsPerPage);
            currencies.addAll(currencyDao.findAll(recordsPerPage, startRecord));
            return currencies;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend all currency", e);
            throw new ServiceException("Database exception during fiend all currency", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<Currency> findAll() throws ServiceException {
        CurrencyDao currencyDao = new CurrencyDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(currencyDao);
        try {
            return currencyDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend all currency", e);
            throw new ServiceException("Database exception during fiend all currency", e);
        } finally {
            transaction.endSingleQuery();
        }
    }
}
