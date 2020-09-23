package com.zagurskaya.cash.model.dao.impl;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.model.dao.AbstractDao;
import com.zagurskaya.cash.model.dao.UserDao;
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

public class UserDaoImpl extends AbstractDao implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String SQL_SELECT_ALL_USERS = "SELECT id, login, password, role FROM `user`";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT id, login, password, role FROM `user` WHERE id= ? ";
    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT id, login, password, role FROM `user` WHERE `login`= ? AND `password`= ?";

    @Override
    public List<User> findAll() throws DAOException {
        Statement statement = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(ColumnName.USER_ID));
                user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
                user.setPassword(resultSet.getString(ColumnName.USER_PASSWORD));
                user.setRole(resultSet.getString(ColumnName.USER_ROLE));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all user", e);
            throw new DAOException("Database exception during fiend all user", e);
        } finally {
            close(statement);
        }
        return users;
    }

    @Override
    public User findById(Long id) throws DAOException {
        PreparedStatement preparedStatement = null;
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(ColumnName.USER_ID));
                user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
                user.setPassword(resultSet.getString(ColumnName.USER_PASSWORD));
                user.setRole(resultSet.getString(ColumnName.USER_ROLE));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend user by id", e);
            throw new DAOException("Database exception during fiend user by id", e);
        } finally {
            close(preparedStatement);
        }
        return user;
    }

    @Override
    public boolean create(User user) throws DAOException {
        return false;
    }

    @Override
    public User read(long id) throws DAOException {
        return null;
    }

    @Override
    public boolean update(User user) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(User user) throws DAOException {
        return false;
    }

    @Override
    public List<User> findAll(String where) throws DAOException {
        return null;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DAOException {
        PreparedStatement preparedStatement = null;
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(ColumnName.USER_ID));
                user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
                user.setPassword(resultSet.getString(ColumnName.USER_PASSWORD));
                user.setRole(resultSet.getString(ColumnName.USER_ROLE));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend user by login and password", e);
            throw new DAOException("Database exception during fiend user by login and password", e);
        } finally {
            close(preparedStatement);
        }
        return user;
    }
}
