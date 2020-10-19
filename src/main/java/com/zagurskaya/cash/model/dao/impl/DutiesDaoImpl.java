package com.zagurskaya.cash.model.dao.impl;

import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.DAOException;
import com.zagurskaya.cash.exception.RepositoryConstraintViolationException;
import com.zagurskaya.cash.model.dao.AbstractDao;
import com.zagurskaya.cash.model.dao.DutiesDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DutiesDaoImpl extends AbstractDao implements DutiesDao {

    private static final Logger logger = LogManager.getLogger(DutiesDaoImpl.class);

    private static final String SQL_SELECT_ALL_DUTIES = "SELECT id, userId, timestamp, number, isClose FROM `duties`  ORDER BY login LIMIT ? Offset ? ";
    private static final String SQL_SELECT_DUTIES_BY_ID = "SELECT id, userId, timestamp, number, isClose FROM `duties` WHERE id= ? ";
    private static final String SQL_SELECT_OPEN_DUTIES_BY_USER_ID = "SELECT id, userId, timestamp, number, isClose FROM `duties` WHERE `userId`=? AND `timestamp` >= ? AND `isClose`=0";
    private static final String SQL_SELECT_CLOSE_DUTIES_BY_USER_ID = "SELECT id, userId, timestamp, number, isClose FROM `duties` WHERE `userId`=? AND `timestamp` >= ? AND `isClose`=1";
    private static final String SQL_INSERT_DUTIES = "INSERT INTO duties(userId, timestamp, number, isClose) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_DUTIES = "UPDATE duties SET userId=?, timestamp = ?, number=?, isClose=? WHERE id= ?";
    private static final String SQL_DELETE_DUTIES = "DELETE FROM duties WHERE id=?";
    private static final String SQL_SELECT_COUNT_DUTIESS = "SELECT COUNT(id) FROM `duties`";

    /**
     * Получение списка смен начиная с startPosition позиции в количестве <= limit
     *
     * @param limit         - количество
     * @param startPosition - начальная позиция
     * @return список смен
     */
    @Override
    public List<Duties> findAll(int limit, int startPosition) throws DAOException {
        List<Duties> dutiesList = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_DUTIES)) {
                preparedStatement.setLong(1, limit);
                preparedStatement.setLong(2, startPosition);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.DUTIES_ID);
                    Long userId = resultSet.getLong(ColumnName.DUTIES_USER_ID);
                    Timestamp timestamp = resultSet.getTimestamp(ColumnName.DUTIES_TIMESTAMP);
                    Integer number = resultSet.getInt(ColumnName.DUTIES_NUMBER);
                    Boolean isClose = resultSet.getBoolean(ColumnName.DUTIES_IS_CLOSE);
                    Duties duties = new Duties
                            .Builder()
                            .addId(id)
                            .addUserId(userId)
                            .addTimestamp(timestamp)
                            .addNumber(number)
                            .addIsClose(isClose)
                            .build();
                    dutiesList.add(duties);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all duties", e);
            throw new DAOException("Database exception during fiend all duties", e);
        }
        return dutiesList;
    }

    /**
     * Поиск смены по ID
     *
     * @param id - ID
     * @return смена
     */
    @Override
    public Duties findById(Long id) throws DAOException {
        Duties duties = null;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_DUTIES_BY_ID)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long userId = resultSet.getLong(ColumnName.DUTIES_USER_ID);
                    Timestamp timestamp = resultSet.getTimestamp(ColumnName.DUTIES_TIMESTAMP);
                    Integer number = resultSet.getInt(ColumnName.DUTIES_NUMBER);
                    Boolean isClose = resultSet.getBoolean(ColumnName.DUTIES_IS_CLOSE);
                    duties = new Duties
                            .Builder()
                            .addId(id)
                            .addUserId(userId)
                            .addTimestamp(timestamp)
                            .addNumber(number)
                            .addIsClose(isClose)
                            .build();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend duties by id", e);
            throw new DAOException("Database exception during fiend duties by id", e);
        }
        return duties;
    }

    /**
     * Создание смены
     *
     * @param duties - смена
     * @return true при успешном создании
     */
    @Override
    public Long create(Duties duties) throws DAOException, RepositoryConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_DUTIES, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, duties.getUserId());
                preparedStatement.setTimestamp(2, duties.getTimestamp());
                preparedStatement.setInt(3, duties.getNumber());
                preparedStatement.setBoolean(4, duties.getIsClose());
                result = preparedStatement.executeUpdate();
                if (1 == result) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RepositoryConstraintViolationException("Duplicate data duties", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during create duties", e);
            throw new DAOException("Database exception during create duties", e);
        }
        return 0L;
    }

    /**
     * Изменение смену
     *
     * @param duties - смена
     * @return true при успешном изменении
     */
    @Override
    public boolean update(Duties duties) throws DAOException, RepositoryConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_DUTIES)) {
                preparedStatement.setLong(1, duties.getUserId());
                preparedStatement.setTimestamp(2, duties.getTimestamp());
                preparedStatement.setInt(3, duties.getNumber());
                preparedStatement.setBoolean(4, duties.getIsClose());
                preparedStatement.setLong(5, duties.getId());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RepositoryConstraintViolationException("Duplicate data duties", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during update duties", e);
            throw new DAOException("Database exception during update duties ", e);
        }
        return 1 == result;
    }

    /**
     * Удаление смену
     *
     * @param duties - смену
     * @return true при успешном удаление
     */
    @Override
    public boolean delete(Duties duties) throws DAOException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_DUTIES)) {
                preparedStatement.setLong(1, duties.getId());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during delete duties", e);
            throw new DAOException("Database exception during delete duties ", e);
        }
        return 1 == result;
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
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COUNT_DUTIESS)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                count = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend count dutiesList row", e);
            throw new DAOException("Database exception during fiend count dutiesList row", e);
        }
        return count;
    }

    /**
     * Получение списка открытых смен пользователя
     *
     * @param userId - код пользователя
     * @param today  - дата
     * @return список смен
     */
    @Override
    public List<Duties> openDutiesUserToday(Long userId, String today) throws DAOException {
        List<Duties> dutiesList = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_OPEN_DUTIES_BY_USER_ID)) {
                preparedStatement.setLong(1, userId);
                preparedStatement.setString(2, today);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.DUTIES_ID);
                    Timestamp timestamp = resultSet.getTimestamp(ColumnName.DUTIES_TIMESTAMP);
                    Integer number = resultSet.getInt(ColumnName.DUTIES_NUMBER);
                    Boolean isClose = resultSet.getBoolean(ColumnName.DUTIES_IS_CLOSE);
                    Duties duties = new Duties
                            .Builder()
                            .addId(id)
                            .addUserId(userId)
                            .addTimestamp(timestamp)
                            .addNumber(number)
                            .addIsClose(isClose)
                            .build();
                    dutiesList.add(duties);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all duties", e);
            throw new DAOException("Database exception during fiend all duties", e);
        }
        return dutiesList;
    }

    @Override
    public Integer numberDutiesToday(User user, String today) throws DAOException {
        List<Duties> dutiesList = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CLOSE_DUTIES_BY_USER_ID)) {
                preparedStatement.setLong(1, user.getId());
                preparedStatement.setString(2, today);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.DUTIES_ID);
                    Timestamp timestamp = resultSet.getTimestamp(ColumnName.DUTIES_TIMESTAMP);
                    Integer number = resultSet.getInt(ColumnName.DUTIES_NUMBER);
                    Boolean isClose = resultSet.getBoolean(ColumnName.DUTIES_IS_CLOSE);
                    Duties duties = new Duties
                            .Builder()
                            .addId(id)
                            .addUserId(user.getId())
                            .addTimestamp(timestamp)
                            .addNumber(number)
                            .addIsClose(isClose)
                            .build();
                    dutiesList.add(duties);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all duties", e);
            throw new DAOException("Database exception during fiend all duties", e);
        }
        return dutiesList.stream().map(Duties::getNumber).max(Integer::compareTo).orElse(0) + 1;
    }

    public Long createAndReturnDutiesId(Duties duties) throws RepositoryConstraintViolationException, DAOException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_DUTIES, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, duties.getUserId());
                preparedStatement.setString(2, duties.getTimestamp().toString());
                preparedStatement.setInt(3, duties.getNumber());
                preparedStatement.setBoolean(4, duties.getIsClose());
                result = preparedStatement.executeUpdate();
                if (1 == result) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RepositoryConstraintViolationException("Duplicate data duties", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during create duties", e);
            throw new DAOException("Database exception during create duties", e);
        }
        return 0L;
    }
}
