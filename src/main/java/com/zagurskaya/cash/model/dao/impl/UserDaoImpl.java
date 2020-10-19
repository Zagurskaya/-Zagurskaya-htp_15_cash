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

    private static final String SQL_SELECT_ALL_USERS_PAGE = "SELECT id, login, password, fullname, role FROM `user`  ORDER BY login LIMIT ? Offset ? ";
    private static final String SQL_SELECT_ALL_USERS = "SELECT id, login, password, fullname, role FROM `user` ";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT id, login, password, fullname, role FROM `user` WHERE id= ? ";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT id, login, password, fullname, role FROM `user` WHERE login= ? ";
    private static final String SQL_INSERT_USER = "INSERT INTO user(login, password, fullname, role) VALUES (?, ?,?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE user SET login=?, fullname = ?, role=? WHERE id= ?";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE id=?";
    private static final String SQL_SELECT_COUNT_USERS = "SELECT COUNT(login) FROM `user`";

    /**
     * Получение списка пользователей начиная с startPosition позиции в количестве <= limit
     *
     * @param limit         - количество
     * @param startPosition - начальная позиция
     * @return список пользователей
     */
    @Override
    public List<User> findAll(int limit, int startPosition) throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USERS_PAGE)) {
                preparedStatement.setLong(1, limit);
                preparedStatement.setLong(2, startPosition);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.USER_ID);
                    String login = resultSet.getString(ColumnName.USER_LOGIN);
                    String password = resultSet.getString(ColumnName.USER_PASSWORD);
                    String fullName = resultSet.getString(ColumnName.USER_FULL_NAME);
                    String role = resultSet.getString(ColumnName.USER_ROLE);
                    User user = new User
                            .Builder()
                            .addId(id)
                            .addLogin(login)
                            .addPassword(password)
                            .addFullName(fullName)
                            .addRole(role)
                            .build();
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all user", e);
            throw new DAOException("Database exception during fiend all user", e);
        }
        return users;
    }

    /**
     * Поиск пользователя по ID
     *
     * @param id - ID
     * @return пользователь
     */
    @Override
    public User findById(Long id) throws DAOException {
        User user = null;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String login = resultSet.getString(ColumnName.USER_LOGIN);
                    String password = resultSet.getString(ColumnName.USER_PASSWORD);
                    String fullName = resultSet.getString(ColumnName.USER_FULL_NAME);
                    String role = resultSet.getString(ColumnName.USER_ROLE);
                    user = new User.Builder()
                            .addId(id)
                            .addLogin(login)
                            .addPassword(password)
                            .addFullName(fullName)
                            .addRole(role)
                            .build();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend user by id", e);
            throw new DAOException("Database exception during fiend user by id", e);
        }
        return user;
    }

    /**
     * Создание пользователя
     *
     * @param user - пользователь
     * @return true при успешном создании
     */
    @Override
    public Long create(User user) throws DAOException, RepositoryConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getFullName());
                preparedStatement.setString(4, user.getRole().getValue());
                result = preparedStatement.executeUpdate();
                if (1 == result) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RepositoryConstraintViolationException("Duplicate data user", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during create user", e);
            throw new DAOException("Database exception during create user", e);
        }
        return 0L;
    }

    /**
     * Изменение пользователя
     *
     * @param user - пользователь
     * @return true при успешном изменении
     */
    @Override
    public boolean update(User user) throws DAOException, RepositoryConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getFullName());
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

    /**
     * Удаление пользователя
     *
     * @param user - пользователь
     * @return true при успешном удаление
     */
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
                    Long id = resultSet.getLong(ColumnName.USER_ID);
                    String password = resultSet.getString(ColumnName.USER_PASSWORD);
                    String fullName = resultSet.getString(ColumnName.USER_FULL_NAME);
                    String role = resultSet.getString(ColumnName.USER_ROLE);
                    user = new User.Builder()
                            .addId(id)
                            .addLogin(login)
                            .addPassword(password)
                            .addFullName(fullName)
                            .addRole(role)
                            .build();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend user by login", e);
            throw new DAOException("Database exception during fiend user by login", e);
        }
        return user;
    }

    /**
     * Количество строк в таблите пользователей
     *
     * @return количество строк
     * @throws DAOException ошибке доступа к базе данных или других ошибках.
     */
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

    /**
     * Получение списка пользователей
     *
     * @return список пользователей
     */
    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USERS)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.USER_ID);
                    String login = resultSet.getString(ColumnName.USER_LOGIN);
                    String password = resultSet.getString(ColumnName.USER_PASSWORD);
                    String fullName = resultSet.getString(ColumnName.USER_FULL_NAME);
                    String role = resultSet.getString(ColumnName.USER_ROLE);
                    User user = new User
                            .Builder()
                            .addId(id)
                            .addLogin(login)
                            .addPassword(password)
                            .addFullName(fullName)
                            .addRole(role)
                            .build();
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all user", e);
            throw new DAOException("Database exception during fiend all user", e);
        }
        return users;
    }
}
