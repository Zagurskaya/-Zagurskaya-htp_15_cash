package com.zagurskaya.cash.model.service.impl;

import com.zagurskaya.cash.entity.Role;
import com.zagurskaya.cash.model.dao.RoleDao;
import com.zagurskaya.cash.model.dao.impl.RoleDaoImpl;
import com.zagurskaya.cash.exception.DAOException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.pool.EntityTransaction;
import com.zagurskaya.cash.model.service.RoleService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);
    private RoleDao roleDao = new RoleDaoImpl();
    private EntityTransaction transaction = new EntityTransaction();

    @Override
    public List<Role> getAll() throws ServiceException {
        transaction.init(roleDao);
        try {
            List<Role> roles = roleDao.findAll();
            transaction.commit();
            return roles;
        } catch (DAOException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during fiend all role", e);
            throw new ServiceException("Database exception during fiend all role", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Role getById(Long id) throws ServiceException {
        transaction.init(roleDao);
        try {
            Role role = roleDao.findById(id);
            transaction.commit();
            return role;
        } catch (DAOException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Database exception during fiend user by id", e);
            throw new ServiceException("Database exception during fiend user by id", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean create(Role role) throws ServiceException {
        return false;
    }

    @Override
    public Role read(long id) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(Role role) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(Role role) throws ServiceException {
        return false;
    }

    @Override
    public List<Role> getAll(String where) throws ServiceException {
        return null;
    }
}
