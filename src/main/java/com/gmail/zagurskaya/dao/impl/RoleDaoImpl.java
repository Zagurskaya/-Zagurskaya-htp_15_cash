package com.gmail.zagurskaya.dao.impl;

import com.gmail.zagurskaya.beans.Role;
import com.gmail.zagurskaya.connection.ConnCreator;
import com.gmail.zagurskaya.dao.AbstractDao;
import com.gmail.zagurskaya.dao.RoleDao;
import com.gmail.zagurskaya.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RoleDaoImpl extends AbstractDao implements RoleDao {

    static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    private static final String SQL_SELECT_ALL_ROLES = "SELECT * FROM `roles`";
    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT * FROM `roles` WHERE id= ? ";

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";

//    private Connection connection ;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;

    @Override
    public List<Role> getAll() throws DAOException {
        List<Role> roles = new ArrayList<>();
        try {
//            connection = ConnCreator.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ROLES);
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong(FIELD_ID));
                role.setName(resultSet.getString(FIELD_NAME));
                roles.add(role);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all role", e);
            throw new DAOException("Database exception during fiend all role", e);
        } finally {
            close(preparedStatement);
//            close(connection);
        }
        return roles;
    }

    @Override
    public Role getById(Long id) throws DAOException {
        Role role = new Role();
        try {
//            connection = ConnCreator.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ROLE_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role.setId(resultSet.getLong(FIELD_ID));
                role.setName(resultSet.getString(FIELD_NAME));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend role by id", e);
            throw new DAOException("Database exception during fiend role by id", e);
        } finally {
            close(preparedStatement);
//            close(connection);
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
    public List<Role> getAll(String where) throws DAOException {
        return null;
    }
}
