package com.zagurskaya.cash.model.dao.impl;

import com.zagurskaya.cash.entity.Role;
import com.zagurskaya.cash.model.dao.AbstractDao;
import com.zagurskaya.cash.model.dao.RoleDao;
import com.zagurskaya.cash.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RoleDaoImpl extends AbstractDao implements RoleDao {

    static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    private static final String SQL_SELECT_ALL_ROLES = "SELECT id, name FROM `roles`";
    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT id, name FROM `roles` WHERE id= ? ";

    @Override
    public List<Role> findAll() throws DAOException {
        Statement statement = null;
        List<Role> roles = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ROLES);
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong(ColumnName.ROLE_ID));
                role.setName(resultSet.getString(ColumnName.ROLE_NAME));
                roles.add(role);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all role", e);
            throw new DAOException("Database exception during fiend all role", e);
        } finally {
            close(statement);
        }
        return roles;
    }

    @Override
    public Role findById(Long id) throws DAOException {
        PreparedStatement preparedStatement = null;
        Role role = new Role();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ROLE_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role.setId(resultSet.getLong(ColumnName.ROLE_ID));
                role.setName(resultSet.getString(ColumnName.ROLE_NAME));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend role by id", e);
            throw new DAOException("Database exception during fiend role by id", e);
        } finally {
            close(preparedStatement);
        }
        return role;
    }

    @Override
    public boolean create(Role role) throws DAOException {
        return false;
    }

    @Override
    public Role read(long id) throws DAOException {
        return null;
    }

    @Override
    public boolean update(Role role) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Role role) throws DAOException {
        return false;
    }

    @Override
    public List<Role> findAll(String where) throws DAOException {
        return null;
    }
}
