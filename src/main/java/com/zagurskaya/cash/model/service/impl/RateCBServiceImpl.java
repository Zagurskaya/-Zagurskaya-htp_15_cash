package com.zagurskaya.cash.model.service.impl;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.RateCB;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.dao.RateCBDao;
import com.zagurskaya.cash.model.dao.impl.RateCBDaoImpl;
import com.zagurskaya.cash.model.service.EntityTransaction;
import com.zagurskaya.cash.model.service.RateCBService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class RateCBServiceImpl implements RateCBService {

    private static final Logger logger = LogManager.getLogger(RateCBServiceImpl.class);

    @Override
    public RateCB findById(Long id) throws ServiceException {
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateCBDao);
        try {
            return rateCBDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend rateCB by id", e);
            throw new ServiceException("Database exception during fiend rateCB by id", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean create(RateCB rateCB) throws ServiceException, CommandException {
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateCBDao);
        try {
            return rateCBDao.create(rateCB) != 0L;
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data rateCB ", e);
            throw new CommandException("Duplicate data rateCB ", e);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during createCheckEn rateCB ", e);
            throw new ServiceException("Database exception during createCheckEn rateCB ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public void create(List<RateCB> rateCBList) throws ServiceException, CommandException {
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(rateCBDao);
        try {
            for (RateCB rateCB : rateCBList) {
                rateCBDao.create(rateCB);
                transaction.commit();
            }
        } catch (DaoConstraintViolationException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Duplicate data rateCB ", e);
            throw new CommandException("Duplicate data rateCB ", e);
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during createCheckEn rateCB ", e);
            throw new ServiceException("Database exception during createCheckEn rateCB ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean update(RateCB rateCB) throws ServiceException, CommandException {
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateCBDao);
        try {
            return rateCBDao.update(rateCB);
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data rateCB ", e);
            throw new CommandException("Duplicate data rateCB ", e);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during update rateCB ", e);
            throw new ServiceException("Database exception during update rateCB ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean delete(RateCB rateCB) throws ServiceException {
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateCBDao);
        try {
            return rateCBDao.delete(rateCB);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during delete rateCB ", e);
            throw new ServiceException("Database exception during delete rateCB ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public int countRows() throws ServiceException {
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateCBDao);
        try {
            return rateCBDao.countRows();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend count rateCBs row", e);
            throw new ServiceException("Database exception during fiend count rateCBs row", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<RateCB> onePartOfListOnPage(int page) throws ServiceException {
        List rateCBs = new ArrayList();
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateCBDao);
        try {
            int recordsPerPage = AttributeName.RECORDS_PER_PAGE;
            int startRecord = (int) Math.ceil((page - 1) * recordsPerPage);
            rateCBs.addAll(rateCBDao.findAll(recordsPerPage, startRecord));
            return rateCBs;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend all rateCB", e);
            throw new ServiceException("Database exception during fiend all rateCB", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public Double rateCBToday(LocalDateTime now, Long coming, Long spending) throws ServiceException {
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateCBDao);
        try {
            return rateCBDao.rateCBToday(now, coming, spending);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend rate CB Today", e);
            throw new ServiceException("Database exception during fiend rate CB Today", e);
        } finally {
            transaction.endSingleQuery();
        }
    }
}
