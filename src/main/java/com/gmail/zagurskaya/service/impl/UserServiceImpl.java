package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.beans.User;
import com.gmail.zagurskaya.dao.UserDao;
import com.gmail.zagurskaya.dao.impl.UserDaoImpl;
import com.gmail.zagurskaya.exception.DAOException;
import com.gmail.zagurskaya.exception.ServiceException;
import com.gmail.zagurskaya.service.AbstractService;
import com.gmail.zagurskaya.service.EntityTransaction;
import com.gmail.zagurskaya.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class UserServiceImpl extends AbstractService implements UserService {

    static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private UserDao userDao = new UserDaoImpl();
    private EntityTransaction transaction = new EntityTransaction();

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws ServiceException {
        transaction.init(userDao);
        try {
            User user = userDao.getUserByLoginAndPassword(login, password);
            transaction.commit();
            return user;
        } catch (DAOException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during fiend user by login and password", e);
            throw new ServiceException("Database exception during fiend user by login and password", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        transaction.init(userDao);
        try {
            List<User> users = userDao.getAll();
            transaction.commit();
            return users;
        } catch (DAOException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during fiend all user", e);
            throw new ServiceException("Database exception during fiend all user", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public User getById(Long id) throws ServiceException {
        transaction.init(userDao);
        try {
            User user = userDao.getById(id);
            transaction.commit();
            return user;
        } catch (DAOException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during fiend user by id", e);
            throw new ServiceException("Database exception during fiend user by id", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean create(User user) throws ServiceException {
        return false;
    }

    @Override
    public User read(long id) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(User user) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(User user) throws ServiceException {
        return false;
    }

    @Override
    public List<User> getAll(String where) throws ServiceException {
        return null;
    }
}
