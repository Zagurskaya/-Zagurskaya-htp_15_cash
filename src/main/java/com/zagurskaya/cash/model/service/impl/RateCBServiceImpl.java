package com.zagurskaya.cash.model.service.impl;

import com.zagurskaya.cash.constant.AttributeConstant;
import com.zagurskaya.cash.entity.RateCB;
import com.zagurskaya.cash.exception.DAOException;
import com.zagurskaya.cash.exception.RepositoryConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.dao.RateCBDao;
import com.zagurskaya.cash.model.dao.impl.RateCBDaoImpl;
import com.zagurskaya.cash.model.service.EntityTransaction;
import com.zagurskaya.cash.model.service.RateCBService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class RateCBServiceImpl implements RateCBService {

    private static final Logger logger = LogManager.getLogger(RateCBServiceImpl.class);

    /**
     * Поиск курса КБ по ID
     *
     * @param id - ID
     * @return курс КБ
     */
    @Override
    public RateCB findById(Long id) throws ServiceException {
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(rateCBDao);
        try {
            return rateCBDao.findById(id);
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during fiend rateCB by id", e);
            throw new ServiceException("Database exception during fiend rateCB by id", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    /**
     * Создание курса КБ
     *
     * @param rateCB - курс КБ
     * @return true при успешном создании
     */
    @Override
    public boolean create(RateCB rateCB) throws ServiceException, ServiceConstraintViolationException {
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(rateCBDao);
        try {
            return rateCBDao.create(rateCB);
        } catch (RepositoryConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data rateCB ", e);
            throw new ServiceConstraintViolationException("Duplicate data rateCB ", e);
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during create rateCB ", e);
            throw new ServiceException("Database exception during create rateCB ", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    /**
     * Изменение курса КБ
     *
     * @param rateCB - курс КБ
     * @return true при успешном изменении
     */
    @Override
    public boolean update(RateCB rateCB) throws ServiceException, ServiceConstraintViolationException {
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(rateCBDao);
        try {
            return rateCBDao.update(rateCB);
        } catch (RepositoryConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data rateCB ", e);
            throw new ServiceConstraintViolationException("Duplicate data rateCB ", e);
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during update rateCB ", e);
            throw new ServiceException("Database exception during update rateCB ", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    /**
     * Удаление курса КБ
     *
     * @param rateCB - курс КБ
     * @return true при успешном удаление
     */
    @Override
    public boolean delete(RateCB rateCB) throws ServiceException {
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(rateCBDao);
        try {
            return rateCBDao.delete(rateCB);
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during delete rateCB ", e);
            throw new ServiceException("Database exception during delete rateCB ", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    /**
     * Количество строк в таблите курсов КБ
     *
     * @return количество строк
     * @throws ServiceException ошибке во время выполнения логическтх блоков и действий.
     */
    @Override
    public Long countRows() throws ServiceException {
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(rateCBDao);
        try {
            return rateCBDao.countRows();
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during fiend count rateCBs row", e);
            throw new ServiceException("Database exception during fiend count rateCBs row", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    /**
     * Получение списка валют КБ на определенной странице
     *
     * @param page - номер страницы
     * @return список пользователей
     */
    @Override
    public List<RateCB> onePartOfListOnPage(int page) throws ServiceException {
        List rateCBs = new ArrayList();
        RateCBDao rateCBDao = new RateCBDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(rateCBDao);
        try {
            int recordsPerPage = AttributeConstant.RECORDS_PER_PAGE;
            int startRecord = (int) Math.ceil((page - 1) * recordsPerPage);
            rateCBs.addAll(rateCBDao.findAll(recordsPerPage, startRecord));
            return rateCBs;
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during fiend all rateCB", e);
            throw new ServiceException("Database exception during fiend all rateCB", e);
        } finally {
            transaction.endSingleRequest();
        }
    }
}
