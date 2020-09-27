package com.zagurskaya.cash.model.dao.impl;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.RepositoryConstraintViolationException;
import com.zagurskaya.cash.model.dao.AbstractDao;
import com.zagurskaya.cash.model.dao.UserDao;
import com.zagurskaya.cash.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String SQL_SELECT_ALL_USERS = "SELECT id, login, password, role FROM `user`";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT id, login, password, role FROM `user` WHERE id= ? ";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT id, login, password, role FROM `user` WHERE login= ? ";
    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT id, login, password, role FROM `user` WHERE `login`= ? AND `password`= ?";
    private static final String SQL_INSERT_USER = "INSERT INTO user(login, password, role) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE user SET login=?, password=?, role=? WHERE id= ?";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE id=?";

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
    public boolean create(User user) throws DAOException, RepositoryConstraintViolationException {
        PreparedStatement preparedStatement = null;
        int result;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            result = preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RepositoryConstraintViolationException("Duplicate data user", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during create user", e);
            throw new DAOException("Database exception during create user", e);
        } finally {
            close(preparedStatement);
        }
        return 1 == result;
    }

    @Override
    public User read(long id) throws DAOException {
        return null;
    }

    @Override
    public boolean update(User user) throws DAOException, RepositoryConstraintViolationException {
        PreparedStatement preparedStatement = null;
        int result;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setLong(4, user.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RepositoryConstraintViolationException("Duplicate data user", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during update user", e);
            throw new DAOException("Database exception during update user ", e);
        } finally {
            close(preparedStatement);
        }
        return 1 == result;
    }

    @Override
    public boolean delete(User user) throws DAOException {
        PreparedStatement preparedStatement = null;
        int result;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setLong(1, user.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during delete user", e);
            throw new DAOException("Database exception during delete user ", e);
        } finally {
            close(preparedStatement);
        }
        return 1 == result;
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

    @Override
    public User findByLogin(String login) throws DAOException {
        PreparedStatement preparedStatement = null;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(ColumnName.USER_ID));
                user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
                user.setPassword(resultSet.getString(ColumnName.USER_PASSWORD));
                user.setRole(resultSet.getString(ColumnName.USER_ROLE));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend user by login", e);
            throw new DAOException("Database exception during fiend user by login", e);
        } finally {
            close(preparedStatement);
        }
        return user;    }
}
