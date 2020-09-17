package com.gmail.zagurskaya.dao.impl;

import com.gmail.zagurskaya.beans.User;
import com.gmail.zagurskaya.connection.ConnCreator;
import com.gmail.zagurskaya.dao.AbstractDao;
import com.gmail.zagurskaya.dao.UserDao;
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

public class UserDaoImpl extends AbstractDao implements UserDao {

    static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM `users`";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM `users` WHERE id= ? ";
    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM `users` WHERE `login`= ? AND `password`= ?";

    private static final String FIELD_ID = "id";
    private static final String FIELD_LOGIN = "login";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_ROLE_ID = "roleId";

//    private Connection connection ;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;

    @Override
    public List<User> getAll() throws DAOException {
        List<User> users = new ArrayList<>();
        try {
//            connection = ConnCreator.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(FIELD_ID));
                user.setLogin(resultSet.getString(FIELD_LOGIN));
                user.setPassword(resultSet.getString(FIELD_PASSWORD));
                user.setRoleId(resultSet.getLong(FIELD_ROLE_ID));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all user", e);
            throw new DAOException("Database exception during fiend all user", e);
        } finally {
            close(preparedStatement);
//            close(connection);
        }
        return users;
    }

    @Override
    public User getById(Long id) throws DAOException {
        User user = new User();
        try {
//            connection = ConnCreator.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(FIELD_ID));
                user.setLogin(resultSet.getString(FIELD_LOGIN));
                user.setPassword(resultSet.getString(FIELD_PASSWORD));
                user.setRoleId(resultSet.getLong(FIELD_ROLE_ID));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend user by id", e);
            throw new DAOException("Database exception during fiend user by id", e);
        } finally {
            close(preparedStatement);
//            close(connection);
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
    public List<User> getAll(String where) throws DAOException {
        return null;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DAOException {
        User user = new User();
        try {
//            connection = ConnCreator.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(FIELD_ID));
                user.setLogin(resultSet.getString(FIELD_LOGIN));
                user.setPassword(resultSet.getString(FIELD_PASSWORD));
                user.setRoleId(resultSet.getLong(FIELD_ROLE_ID));
            }
        } catch (SQLException e) {
            throw new DAOException("Database exception during fiend user by login and password", e);
        } finally {
            close(preparedStatement);
//            close(connection);
        }
        return user;
    }
}
