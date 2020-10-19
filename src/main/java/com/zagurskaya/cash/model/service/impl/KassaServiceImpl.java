package com.zagurskaya.cash.model.service.impl;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.Kassa;
import com.zagurskaya.cash.entity.SprEntry;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.dao.KassaDao;
import com.zagurskaya.cash.model.dao.SprEntryDao;
import com.zagurskaya.cash.model.dao.impl.KassaDaoImpl;
import com.zagurskaya.cash.model.dao.impl.SprEntryDaoImpl;
import com.zagurskaya.cash.model.service.EntityTransaction;
import com.zagurskaya.cash.model.service.KassaService;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
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
        } catch (DaoException e) {
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
            return kassaDao.create(kassa) != null;
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data kassa ", e);
            throw new ServiceConstraintViolationException("Duplicate data kassa ", e);
        } catch (DaoException e) {
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
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data kassa ", e);
            throw new ServiceConstraintViolationException("Duplicate data kassa ", e);
        } catch (DaoException e) {
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
        } catch (DaoException e) {
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
            return kassaDao.countRows();
        } catch (DaoException e) {
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
            int recordsPerPage = AttributeName.RECORDS_PER_PAGE;
            int startRecord = (int) Math.ceil((page - 1) * recordsPerPage);
            kassaList.addAll(kassaDao.findAll(recordsPerPage, startRecord));
            return kassaList;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend all kassa", e);
            throw new ServiceException("Database exception during fiend all kassa", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    //внутрикассовые операции
    public boolean updateKassaInSideOperation(Date date, Long dutiesId, Long currencyId, Double sum, Long sprOperationsId) throws ServiceConstraintViolationException, ServiceException {
        SprEntryDao sprEntryDao = new SprEntryDaoImpl();
        KassaDao kassaDao = new KassaDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(sprEntryDao, kassaDao);
        try {
            List<SprEntry> sprEntries = sprEntryDao.findAllBySprOperationIdAndCurrencyId(sprOperationsId, currencyId);

            Kassa kassa = kassaDao.findByCurrencyIdAndDateAndDutiesId(date, dutiesId, currencyId);
            Double kassasComing = kassa.getComing();
            Double kassasSpending = kassa.getSpending();
            Double kassaBalance = kassa.getBalance();

            Kassa.Builder updateKassaBuilder = new Kassa
                    .Builder()
                    .addId(kassa.getId())
                    .addСurrencyId(currencyId)
                    .addReceived(kassa.getReceived())
                    .addTransmitted(kassa.getTransmitted())
                    .addUserId(kassa.getUserId())
                    .addData(date)
                    .addDutiesId(dutiesId);

            Kassa updateKassa;
            if (sprEntries.get(0).getIsSpending()) {
                updateKassa = updateKassaBuilder
                        .addСoming(kassa.getComing())
                        .addSpending(DataUtil.round(kassasSpending + sum))
                        .addBalance(DataUtil.round(kassaBalance - sum))
                        .build();
            } else {
                updateKassa = updateKassaBuilder
                        .addСoming(DataUtil.round(kassasComing + sum))
                        .addSpending(kassa.getSpending())
                        .addBalance(DataUtil.round(kassaBalance + sum))
                        .build();
            }
            boolean result = kassaDao.update(updateKassa);
            transaction.commit();
            return result;
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data kassa ", e);
            throw new ServiceConstraintViolationException("Duplicate data kassa ", e);
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during update Kassa InSide Operation", e);
            throw new ServiceException("Database exception during update Kassa InSide Operation", e);
        } finally {
            transaction.end();
        }
    }

    //внекассовые операции
    @Override
    public boolean updateKassaOutSideOperation(Date date, Long dutiesId, Long currencyId, Double sum, Long sprOperationsId) throws ServiceException, ServiceConstraintViolationException {

        SprEntryDao sprEntryDao = new SprEntryDaoImpl();
        KassaDao kassaDao = new KassaDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(sprEntryDao, kassaDao);
        try {
            List<SprEntry> sprEntries = sprEntryDao.findAllBySprOperationIdAndCurrencyId(sprOperationsId, currencyId);

            Kassa kassa = kassaDao.findByCurrencyIdAndDateAndDutiesId(date, dutiesId, currencyId);

            Double kassasReceived = kassa.getReceived();
            Double kassasTransmitted = kassa.getTransmitted();
            Double kassaBalance = kassa.getBalance();


            Kassa.Builder updateKassaBuilder = new Kassa
                    .Builder()
                    .addId(kassa.getId())
                    .addСurrencyId(currencyId)
                    .addСoming(kassa.getComing())
                    .addSpending(kassa.getSpending())
                    .addUserId(kassa.getUserId())
                    .addData(date)
                    .addDutiesId(dutiesId);

            Kassa updateKassa;
            if (sprEntries.get(0).getIsSpending()) {
                updateKassa = updateKassaBuilder
                        .addReceived(kassa.getReceived())
                        .addTransmitted(DataUtil.round(kassasTransmitted + sum))
                        .addBalance(DataUtil.round(kassaBalance - sum))
                        .build();
            } else {
                updateKassa = updateKassaBuilder
                        .addReceived(DataUtil.round(kassasReceived + sum))
                        .addTransmitted(kassa.getTransmitted())
                        .addBalance(DataUtil.round(kassaBalance + sum))
                        .build();
            }
            boolean result = kassaDao.update(updateKassa);
            transaction.commit();
            return result;
        } catch (DaoConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data kassa ", e);
            throw new ServiceConstraintViolationException("Duplicate data kassa ", e);
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during update Kassa Out Side Operation", e);
            throw new ServiceException("Database exception during update Kassa out Side Operation", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Kassa> getBallance(User user, Duties duties) throws ServiceException {
        List kassaList = new ArrayList();
        KassaDao kassaDao = new KassaDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(kassaDao);
        try {
            kassaList.addAll(kassaDao.findAllByUserIdAndDutiesId(user.getId(), duties.getId()));
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during getBallance", e);
            throw new ServiceException("Database exception during getBallance", e);
        } finally {
            transaction.endSingleRequest();
        }
        return kassaList;
    }

}
