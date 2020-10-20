package com.zagurskaya.cash.model.service.impl;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.RateNB;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.dao.RateNBDao;
import com.zagurskaya.cash.model.dao.impl.RateNBDaoImpl;
import com.zagurskaya.cash.model.service.EntityTransaction;
import com.zagurskaya.cash.model.service.RateNBService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class RateNBServiceImpl implements RateNBService {

    private static final Logger logger = LogManager.getLogger(RateNBServiceImpl.class);

    /**
     * Поиск курса НБ по ID
     *
     * @param id - ID
     * @return курс НБ
     */
    @Override
    public RateNB findById(Long id) throws ServiceException {
        RateNBDao rateNBDao = new RateNBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateNBDao);
        try {
            return rateNBDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend rateNB by id", e);
            throw new ServiceException("Database exception during fiend rateNB by id", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    /**
     * Создание курса НБ
     *
     * @param rateNB - курс НБ
     * @return true при успешном создании
     */
    @Override
    public boolean create(RateNB rateNB) throws ServiceException, ServiceConstraintViolationException {
        RateNBDao rateNBDao = new RateNBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateNBDao);
        try {
            return rateNBDao.create(rateNB) != 0L;
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data rateNB ", e);
            throw new ServiceConstraintViolationException("Duplicate data rateNB ", e);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during create rateNB ", e);
            throw new ServiceException("Database exception during create rateNB ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    /**
     * Изменение курса НБ
     *
     * @param rateNB - курс НБ
     * @return true при успешном изменении
     */
    @Override
    public boolean update(RateNB rateNB) throws ServiceException, ServiceConstraintViolationException {
        RateNBDao rateNBDao = new RateNBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateNBDao);
        try {
            return rateNBDao.update(rateNB);
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data rateNB ", e);
            throw new ServiceConstraintViolationException("Duplicate data rateNB ", e);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during update rateNB ", e);
            throw new ServiceException("Database exception during update rateNB ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    /**
     * Удаление курса НБ
     *
     * @param rateNB - курс НБ
     * @return true при успешном удаление
     */
    @Override
    public boolean delete(RateNB rateNB) throws ServiceException {
        RateNBDao rateNBDao = new RateNBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateNBDao);
        try {
            return rateNBDao.delete(rateNB);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during delete rateNB ", e);
            throw new ServiceException("Database exception during delete rateNB ", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    /**
     * Количество строк в таблите курсов НБ
     *
     * @return количество строк
     * @throws ServiceException ошибке во время выполнения логическтх блоков и действий.
     */
    @Override
    public int countRows() throws ServiceException {
        RateNBDao rateNBDao = new RateNBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateNBDao);
        try {
            return rateNBDao.countRows();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend count rateNBs row", e);
            throw new ServiceException("Database exception during fiend count rateNBs row", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    /**
     * Получение списка валют НБ на определенной странице
     *
     * @param page - номер страницы
     * @return список пользователей
     */
    @Override
    public List<RateNB> onePartOfListOnPage(int page) throws ServiceException {
        List rateNBs = new ArrayList();
        RateNBDao rateNBDao = new RateNBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(rateNBDao);
        try {
            int recordsPerPage = AttributeName.RECORDS_PER_PAGE;
            int startRecord = (int) Math.ceil((page - 1) * recordsPerPage);
            rateNBs.addAll(rateNBDao.findAll(recordsPerPage, startRecord));
            return rateNBs;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend all rateNB", e);
            throw new ServiceException("Database exception during fiend all rateNB", e);
        } finally {
            transaction.endSingleQuery();
        }
    }
}
