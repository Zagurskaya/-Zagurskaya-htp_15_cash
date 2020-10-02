package com.zagurskaya.cash.model.dao.impl;

import com.zagurskaya.cash.entity.RoleEnum;
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
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String SQL_SELECT_ALL_USERS = "SELECT id, login, password, role FROM `user`  ORDER BY login LIMIT ? Offset ? ";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT id, login, password, role FROM `user` WHERE id= ? ";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT id, login, password, role FROM `user` WHERE login= ? ";
    private static final String SQL_INSERT_USER = "INSERT INTO user(login, password, role) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE user SET login=?, password=?, role=? WHERE id= ?";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE id=?";
    private static final String SQL_SELECT_COUNT_USERS = "SELECT COUNT(login) FROM `user`";

    @Override
    public List<User> findAll(int limit, int startPosition) throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USERS)) {
                preparedStatement.setLong(1, limit);
                preparedStatement.setLong(2, startPosition);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong(ColumnName.USER_ID));
                    user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
                    user.setPassword(resultSet.getString(ColumnName.USER_PASSWORD));
                    user.setRole(RoleEnum.valueOf(resultSet.getString(ColumnName.USER_ROLE).toUpperCase()));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all user", e);
            throw new DAOException("Database exception during fiend all user", e);
        }
        return users;
    }

    @Override
    public User findById(Long id) throws DAOException {
        User user = new User();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user.setId(resultSet.getLong(ColumnName.USER_ID));
                    user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
                    user.setPassword(resultSet.getString(ColumnName.USER_PASSWORD));
                    user.setRole(RoleEnum.valueOf(resultSet.getString(ColumnName.USER_ROLE).toUpperCase()));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend user by id", e);
            throw new DAOException("Database exception during fiend user by id", e);
        }
        return user;
    }

    @Override
    public boolean create(User user) throws DAOException, RepositoryConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER);) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getRole().getValue());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RepositoryConstraintViolationException("Duplicate data user", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during create user", e);
            throw new DAOException("Database exception during create user", e);
        }
        return 1 == result;
    }

    @Override
    public boolean update(User user) throws DAOException, RepositoryConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getRole().getValue());
                preparedStatement.setLong(4, user.getId());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RepositoryConstraintViolationException("Duplicate data user", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during update user", e);
            throw new DAOException("Database exception during update user ", e);
        }
        return 1 == result;
    }

    @Override
    public boolean delete(User user) throws DAOException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)) {
                preparedStatement.setLong(1, user.getId());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during delete user", e);
            throw new DAOException("Database exception during delete user ", e);
        }
        return 1 == result;
    }

    @Override
    public User findByLogin(String login) throws DAOException {
        User user = null;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)) {
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong(ColumnName.USER_ID));
                    user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
                    user.setPassword(resultSet.getString(ColumnName.USER_PASSWORD));
                    user.setRole(RoleEnum.valueOf(resultSet.getString(ColumnName.USER_ROLE).toUpperCase()));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend user by login", e);
            throw new DAOException("Database exception during fiend user by login", e);
        }
        return user;
    }

    @Override
    public Long countRows() throws DAOException {
        Long count;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COUNT_USERS)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                count = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend count users row", e);
            throw new DAOException("Database exception during fiend count users row", e);
        }
        return count;
    }
}
