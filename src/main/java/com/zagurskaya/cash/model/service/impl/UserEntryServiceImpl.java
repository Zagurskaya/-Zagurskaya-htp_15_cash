package com.zagurskaya.cash.model.service.impl;

import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.dao.UserEntryDao;
import com.zagurskaya.cash.model.dao.impl.UserEntryDaoImpl;
import com.zagurskaya.cash.model.service.EntityTransaction;
import com.zagurskaya.cash.model.service.UserEntryService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;


public class UserEntryServiceImpl implements UserEntryService {

    private static final Logger logger = LogManager.getLogger(UserEntryServiceImpl.class);

    @Override
    public List<UserEntry> findAllToday() throws ServiceException {
        LocalDate date = LocalDate.now();
        UserEntryDao userEntryDao = new UserEntryDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userEntryDao);
        try {
            return userEntryDao.findAllByData(date);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Database exception during fiend all user", e);
            throw new ServiceException("Database exception during fiend all user", e);
        } finally {
            transaction.endSingleQuery();
        }
    }


}
