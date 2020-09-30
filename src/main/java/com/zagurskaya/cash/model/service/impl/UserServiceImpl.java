package com.zagurskaya.cash.model.service.impl;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.RepositoryConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.model.dao.UserDao;
import com.zagurskaya.cash.model.dao.impl.UserDaoImpl;
import com.zagurskaya.cash.exception.DAOException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.EntityTransaction;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.util.AttributeConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final String HASH_ALGORITHM = "SHA-512";

    @Override
    public User getUserByLoginAndValidPassword(String login, String password) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(userDao);
        User user;
        try {
            user = userDao.findByLogin(login);
            return user != null && user.getPassword().equals(getHash(password)) ? user : null;
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during fiend user by login and password", e);
            throw new ServiceException("Database exception during fiend user by login and password", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

//    private List<User> findAll(int limit, int startPosition) throws ServiceException {
//        UserDao userDao = new UserDaoImpl();
//        EntityTransaction transaction = new EntityTransaction();
//        transaction.initSingleRequest(userDao);
//        try {
//            List<User> users = userDao.findAll(limit, startPosition);
////            transaction.commit();
//            return users;
//        } catch (DAOException e) {
////            transaction.rollback();
//            logger.log(Level.ERROR, "Database exception during fiend all user", e);
//            throw new ServiceException("Database exception during fiend all user", e);
//        } finally {
//            transaction.endSingleRequest();
//        }
//    }

    @Override
    public User findById(Long id) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(userDao);
        try {
            User user = userDao.findById(id);
            return user;
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during fiend user by id", e);
            throw new ServiceException("Database exception during fiend user by id", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    @Override
    public boolean create(User user) throws ServiceException, ServiceConstraintViolationException {
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(userDao);
        try {
            if (userDao.findByLogin(user.getLogin()) == null) {
                String hashPassword = getHash(user.getPassword());
                user.setPassword(hashPassword);
                return userDao.create(user);
            } else {
                logger.log(Level.ERROR, "Duplicate data user's login ");
                throw new ServiceConstraintViolationException("Duplicate data user's login ");
            }
        } catch (RepositoryConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data user ", e);
            throw new ServiceConstraintViolationException("Duplicate data user ", e);
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during create user ", e);
            throw new ServiceException("Database exception during create user ", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    @Override
    public boolean update(User user) throws ServiceException, ServiceConstraintViolationException {
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(userDao);
        try {
            return userDao.update(user);
        } catch (RepositoryConstraintViolationException e) {
            logger.log(Level.ERROR, "Duplicate data user ", e);
            throw new ServiceConstraintViolationException("Duplicate data user ", e);
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during update user ", e);
            throw new ServiceException("Database exception during update user ", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    @Override
    public boolean delete(User user) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(userDao);
        try {
            return userDao.delete(user);
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during delete user ", e);
            throw new ServiceException("Database exception during delete user ", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    @Override
    public Long countRows() throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(userDao);
        try {
            Long count = userDao.countRows();
            return count;
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during fiend count users row", e);
            throw new ServiceException("Database exception during fiend count users row", e);
        } finally {
            transaction.endSingleRequest();
        }
    }

    @Override
    public List<User> onePartOfUsersListOnPage(int page) throws ServiceException {
        List users = new ArrayList();
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleRequest(userDao);
        try {
            int recordsPerPage = AttributeConstant.RECORDS_PER_PAGE;
            int startRecord = (int) Math.ceil((page - 1) * recordsPerPage);
            users.addAll(userDao.findAll(recordsPerPage, startRecord));
            return users;
        } catch (DAOException e) {
            logger.log(Level.ERROR, "Database exception during fiend all user", e);
            throw new ServiceException("Database exception during fiend all user", e);
        } finally {
            transaction.endSingleRequest();
        }
    }


    public static String getHash(String password) throws ServiceException {
        String hashPassword;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] bytesMessageDigest = messageDigest.digest(password.getBytes());
            BigInteger no = new BigInteger(1, bytesMessageDigest);
            hashPassword = no.toString(16);
            while (hashPassword.length() < 32) {
                hashPassword = "0" + hashPassword;
            }

        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.ERROR, "No such algorithm " + HASH_ALGORITHM, e);
            throw new ServiceException("No such algorithm " + HASH_ALGORITHM, e);
        }
        return hashPassword;
    }
}
