package com.zagurskaya.cash.model.service.impl;

import com.zagurskaya.cash.constant.AttributeConstant;
import com.zagurskaya.cash.entity.Kassa;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.DAOException;
import com.zagurskaya.cash.exception.RepositoryConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.dao.KassaDao;
import com.zagurskaya.cash.model.dao.impl.KassaDaoImpl;
import com.zagurskaya.cash.model.service.KassaService;
import com.zagurskaya.cash.model.service.EntityTransaction;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class KassaServiceImpl implements KassaService {

    private static final Logger logger = LogManager.getLogger(KassaServiceImpl.class);

    /**
     * Поиск записи в картотеке касса по ID
     *
     * @param id - ID
     * @return запись в картотеке касса
     */
    @Override
    public Kassa findById(Long id) throws ServiceException {
        KassaDao kassaDao = new KassaDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(kassaDao);
        try {
            Kassa kassa = kassaDao.findById(id);
            return kassa;
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during fiend kassa by id", e);
            throw new ServiceException("Database exception during fiend kassa by id", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    /**
     * Создание записи в картотеке касса
     *
     * @param kassa - запись в картотеке касса
     * @return true при успешном создании
     */
    @Override
    public boolean create(Kassa kassa) throws ServiceException, ServiceConstraintViolationException {
        KassaDao kassaDao = new KassaDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(kassaDao);
        try {
            return kassaDao.create(kassa);
        } catch (RepositoryConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data kassa ", e);
            throw new ServiceConstraintViolationException("Duplicate data kassa ", e);
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during create kassa ", e);
            throw new ServiceException("Database exception during create kassa ", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    /**
     * Изменение записи в картотеке касса
     *
     * @param kassa - запись в картотеке касса
     * @return true при успешном изменении
     */
    @Override
    public boolean update(Kassa kassa) throws ServiceException, ServiceConstraintViolationException {
        KassaDao kassaDao = new KassaDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(kassaDao);
        try {
            return kassaDao.update(kassa);
        } catch (RepositoryConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data kassa ", e);
            throw new ServiceConstraintViolationException("Duplicate data kassa ", e);
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during update kassa ", e);
            throw new ServiceException("Database exception during update kassa ", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    /**
     * Удаление записи в картотеке касса
     *
     * @param kassa - запись в картотеке касса
     * @return true при успешном удаление
     */
    @Override
    public boolean delete(Kassa kassa) throws ServiceException {
        KassaDao kassaDao = new KassaDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(kassaDao);
        try {
            return kassaDao.delete(kassa);
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during delete kassa ", e);
            throw new ServiceException("Database exception during delete kassa ", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    /**
     * Количество строк картотеке касса
     *
     * @return количество строк
     * @throws ServiceException ошибке во время выполнения логическтх блоков и действий.
     */
    @Override
    public Long countRows() throws ServiceException {
        KassaDao kassaDao = new KassaDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(kassaDao);
        try {
            Long count = kassaDao.countRows();
            return count;
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during fiend count kassaList row", e);
            throw new ServiceException("Database exception during fiend count kassaList row", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    /**
     * Получение списка записие в картотеке касса на определенной странице
     *
     * @param page - номер страницы
     * @return список смен
     */
    @Override
    public List<Kassa> onePartOfListOnPage(int page) throws ServiceException {
        List kassaList = new ArrayList();
        KassaDao kassaDao = new KassaDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(kassaDao);
        try {
            int recordsPerPage = AttributeConstant.RECORDS_PER_PAGE;
            int startRecord = (int) Math.ceil((page - 1) * recordsPerPage);
            kassaList.addAll(kassaDao.findAll(recordsPerPage, startRecord));
            return kassaList;
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during fiend all kassa", e);
            throw new ServiceException("Database exception during fiend all kassa", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

}
