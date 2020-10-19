package com.zagurskaya.cash.model.dao.impl;

import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.DAOException;
import com.zagurskaya.cash.exception.RepositoryConstraintViolationException;
import com.zagurskaya.cash.model.dao.AbstractDao;
import com.zagurskaya.cash.model.dao.UserOperationDao;
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

public class UserOperationDaoImpl extends AbstractDao implements UserOperationDao {

    private static final Logger logger = LogManager.getLogger(UserOperationDaoImpl.class);

    private static final String SQL_SELECT_ALL_USER_OPERATION_PAGE =
            "SELECT id, timestamp, rate, sum, currencyId, userId, dutiesId, operationId, specification, checkingAccount, fullName FROM userOperation  ORDER BY id LIMIT ? Offset ? ";
    private static final String SQL_SELECT_ALL_BY_USER_ID_AND_DUTIES_ID_PAGE =
            "SELECT id, timestamp, rate, sum, currencyId, userId, dutiesId, operationId, specification, checkingAccount, fullName FROM userOperation WHERE userId = ? AND  dutiesId = ? ORDER BY id LIMIT ? Offset ? ";
    private static final String SQL_SELECT_ALL_BY_USER_ID_AND_DUTIES_ID =
            "SELECT id, timestamp, rate, sum, currencyId, userId, dutiesId, operationId, specification, checkingAccount, fullName FROM userOperation WHERE userId = ? AND  dutiesId = ? ORDER BY id ";
    private static final String SQL_SELECT_ALL_USER_OPERATION =
            "SELECT id, timestamp, rate, sum, currencyId, userId, dutiesId, operationId, specification, checkingAccount, fullName FROM userOperation ";
    private static final String SQL_SELECT_USER_OPERATION_BY_ID =
            "SELECT id, timestamp, rate, sum, currencyId, userId, dutiesId, operationId, specification, checkingAccount, fullName FROM userOperation WHERE id= ? ";
    private static final String SQL_INSERT_USER_OPERATION =
            "INSERT INTO userOperation(timestamp, rate, sum, currencyId, userId, dutiesId, operationId, specification, checkingAccount, fullName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER_OPERATION =
            "UPDATE userOperation SET timestamp=?, rate = ?, sum = ?, currencyId = ?, userId = ?, dutiesId = ?, operationId = ?, specification = ?, checkingAccount = ? , fullName = ?  WHERE id= ?";
    private static final String SQL_DELETE_USER_OPERATION =
            "DELETE FROM userOperation WHERE id=?";
    private static final String SQL_SELECT_COUNT_USER_OPERATION = "SELECT COUNT(id) FROM userOperation";

    /**
     * Получение списка операций начиная с startPosition позиции в количестве <= limit
     *
     * @param limit         - количество
     * @param startPosition - начальная позиция
     * @return список операций
     */
    @Override
    public List<UserOperation> findAll(int limit, int startPosition) throws DAOException {
        List<UserOperation> userOperations = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USER_OPERATION_PAGE)) {
                preparedStatement.setLong(1, limit);
                preparedStatement.setLong(2, startPosition);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.USER_OPERATION_ID);
                    Timestamp timestamp = resultSet.getTimestamp(ColumnName.USER_OPERATION_TIMESTAMP);
                    Double rate = resultSet.getDouble(ColumnName.USER_OPERATION_RATE);
                    Double sum = resultSet.getDouble(ColumnName.USER_OPERATION_SUM);
                    Long currencyId = resultSet.getLong(ColumnName.USER_OPERATION_CURRENCY_ID);
                    Long userId = resultSet.getLong(ColumnName.USER_OPERATION_USER_ID);
                    Long dutiesId = resultSet.getLong(ColumnName.USER_OPERATION_DUTIES_ID);
                    Long operationId = resultSet.getLong(ColumnName.USER_OPERATION_OPERATION_ID);
                    String specification = resultSet.getString(ColumnName.USER_OPERATION_SPECIFICATION);
                    String checkingAccount = resultSet.getString(ColumnName.USER_OPERATION_CHECKING_ACCOUNT);
                    String fullName = resultSet.getString(ColumnName.USER_OPERATION_FULL_NAME);
                    UserOperation userOperation = new UserOperation
                            .Builder()
                            .addId(id)
                            .addTimestamp(timestamp)
                            .addRate(rate)
                            .addSum(sum)
                            .addCurrencyId(currencyId)
                            .addUserId(userId)
                            .addDutiesId(dutiesId)
                            .addOperationId(operationId)
                            .addSpecification(specification)
                            .addCheckingAccount(checkingAccount)
                            .addFullName(fullName)
                            .build();
                    userOperations.add(userOperation);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all userOperation", e);
            throw new DAOException("Database exception during fiend all userOperation", e);
        }
        return userOperations;
    }

    /**
     * Поиск операции по ID
     *
     * @param id - ID
     * @return операция
     */
    @Override
    public UserOperation findById(Long id) throws DAOException {
        UserOperation userOperation = null;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_OPERATION_BY_ID)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Timestamp timestamp = resultSet.getTimestamp(ColumnName.USER_OPERATION_TIMESTAMP);
                    Double rate = resultSet.getDouble(ColumnName.USER_OPERATION_RATE);
                    Double sum = resultSet.getDouble(ColumnName.USER_OPERATION_SUM);
                    Long currencyId = resultSet.getLong(ColumnName.USER_OPERATION_CURRENCY_ID);
                    Long userId = resultSet.getLong(ColumnName.USER_OPERATION_USER_ID);
                    Long dutiesId = resultSet.getLong(ColumnName.USER_OPERATION_DUTIES_ID);
                    Long operationId = resultSet.getLong(ColumnName.USER_OPERATION_OPERATION_ID);
                    String specification = resultSet.getString(ColumnName.USER_OPERATION_SPECIFICATION);
                    String checkingAccount = resultSet.getString(ColumnName.USER_OPERATION_CHECKING_ACCOUNT);
                    String fullName = resultSet.getString(ColumnName.USER_OPERATION_FULL_NAME);
                    userOperation = new UserOperation
                            .Builder()
                            .addId(id)
                            .addTimestamp(timestamp)
                            .addRate(rate)
                            .addSum(sum)
                            .addCurrencyId(currencyId)
                            .addUserId(userId)
                            .addDutiesId(dutiesId)
                            .addOperationId(operationId)
                            .addSpecification(specification)
                            .addCheckingAccount(checkingAccount)
                            .addFullName(fullName)
                            .build();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend userOperation by id", e);
            throw new DAOException("Database exception during fiend userOperation by id", e);
        }
        return userOperation;
    }

    /**
     * Создание пользователя
     *
     * @param userOperation - пользователь
     * @return true при успешном создании
     */
    @Override
    public Long create(UserOperation userOperation) throws DAOException, RepositoryConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER_OPERATION, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, userOperation.getTimestamp().toString());
                preparedStatement.setDouble(2, userOperation.getRate());
                preparedStatement.setDouble(3, userOperation.getSum());
                preparedStatement.setLong(4, userOperation.getCurrencyId());
                preparedStatement.setLong(5, userOperation.getUserId());
                preparedStatement.setLong(6, userOperation.getDutiesId());
                preparedStatement.setLong(7, userOperation.getOperationId());
                preparedStatement.setString(8, userOperation.getSpecification());
                preparedStatement.setString(9, userOperation.getCheckingAccount());
                preparedStatement.setString(10, userOperation.getFullName());
                result = preparedStatement.executeUpdate();
                if (1 == result) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RepositoryConstraintViolationException("Duplicate data userOperation", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during create userOperation", e);
            throw new DAOException("Database exception during create userOperation", e);
        }
        return 0L;
    }

    /**
     * Изменение операции
     *
     * @param userOperation - операция
     * @return true при успешном изменении
     */
    @Override
    public boolean update(UserOperation userOperation) throws DAOException, RepositoryConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_OPERATION)) {
                preparedStatement.setString(1, userOperation.getTimestamp().toString());
                preparedStatement.setDouble(2, userOperation.getRate());
                preparedStatement.setDouble(3, userOperation.getSum());
                preparedStatement.setLong(4, userOperation.getCurrencyId());
                preparedStatement.setLong(5, userOperation.getUserId());
                preparedStatement.setLong(6, userOperation.getDutiesId());
                preparedStatement.setLong(7, userOperation.getOperationId());
                preparedStatement.setString(8, userOperation.getSpecification());
                preparedStatement.setString(9, userOperation.getCheckingAccount());
                preparedStatement.setString(10, userOperation.getFullName());
                preparedStatement.setLong(11, userOperation.getId());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RepositoryConstraintViolationException("Duplicate data userOperation", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during update userOperation", e);
            throw new DAOException("Database exception during update userOperation ", e);
        }
        return 1 == result;
    }

    /**
     * Удаление операции
     *
     * @param userOperation - операции
     * @return true при успешном удаление
     */
    @Override
    public boolean delete(UserOperation userOperation) throws DAOException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_OPERATION)) {
                preparedStatement.setLong(1, userOperation.getId());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during delete userOperation", e);
            throw new DAOException("Database exception during delete userOperation ", e);
        }
        return 1 == result;
    }

    /**
     * Количество строк в таблите операций
     *
     * @return количество строк
     * @throws DAOException ошибке доступа к базе данных или других ошибках.
     */
    @Override
    public Long countRows() throws DAOException {
        Long count;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COUNT_USER_OPERATION)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                count = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend count userOperations row", e);
            throw new DAOException("Database exception during fiend count userOperations row", e);
        }
        return count;
    }

    /**
     * Получение списка операций
     *
     * @return список операций
     */
    @Override
    public List<UserOperation> findAll() throws DAOException {
        List<UserOperation> userOperations = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USER_OPERATION)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.USER_OPERATION_ID);
                    Timestamp timestamp = resultSet.getTimestamp(ColumnName.USER_OPERATION_TIMESTAMP);
                    Double rate = resultSet.getDouble(ColumnName.USER_OPERATION_RATE);
                    Double sum = resultSet.getDouble(ColumnName.USER_OPERATION_SUM);
                    Long currencyId = resultSet.getLong(ColumnName.USER_OPERATION_CURRENCY_ID);
                    Long userId = resultSet.getLong(ColumnName.USER_OPERATION_USER_ID);
                    Long dutiesId = resultSet.getLong(ColumnName.USER_OPERATION_DUTIES_ID);
                    Long operationId = resultSet.getLong(ColumnName.USER_OPERATION_OPERATION_ID);
                    String specification = resultSet.getString(ColumnName.USER_OPERATION_SPECIFICATION);
                    String checkingAccount = resultSet.getString(ColumnName.USER_OPERATION_CHECKING_ACCOUNT);
                    String fullName = resultSet.getString(ColumnName.USER_OPERATION_FULL_NAME);
                    UserOperation userOperation = new UserOperation
                            .Builder()
                            .addId(id)
                            .addTimestamp(timestamp)
                            .addRate(rate)
                            .addSum(sum)
                            .addCurrencyId(currencyId)
                            .addUserId(userId)
                            .addDutiesId(dutiesId)
                            .addOperationId(operationId)
                            .addSpecification(specification)
                            .addCheckingAccount(checkingAccount)
                            .addFullName(fullName)
                            .build();
                    userOperations.add(userOperation);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all userOperation", e);
            throw new DAOException("Database exception during fiend all userOperation", e);
        }
        return userOperations;
    }

    @Override
    public List<UserOperation> findAllByUserIdAndDutiesId(Long userId, Long dutiesId, int limit, int startPosition) throws DAOException {
        List<UserOperation> userOperations = new ArrayList<>();
        boolean isPart = (0 != limit) && (0 != startPosition);
        String SQL = isPart ? SQL_SELECT_ALL_BY_USER_ID_AND_DUTIES_ID_PAGE : SQL_SELECT_ALL_BY_USER_ID_AND_DUTIES_ID;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                preparedStatement.setLong(1, userId);
                preparedStatement.setLong(2, dutiesId);
                if (isPart) {
                    preparedStatement.setLong(3, limit);
                    preparedStatement.setLong(4, startPosition);
                }
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.USER_OPERATION_ID);
                    Timestamp timestamp = resultSet.getTimestamp(ColumnName.USER_OPERATION_TIMESTAMP);
                    Double rate = resultSet.getDouble(ColumnName.USER_OPERATION_RATE);
                    Double sum = resultSet.getDouble(ColumnName.USER_OPERATION_SUM);
                    Long currencyId = resultSet.getLong(ColumnName.USER_OPERATION_CURRENCY_ID);
                    Long operationId = resultSet.getLong(ColumnName.USER_OPERATION_OPERATION_ID);
                    String specification = resultSet.getString(ColumnName.USER_OPERATION_SPECIFICATION);
                    String checkingAccount = resultSet.getString(ColumnName.USER_OPERATION_CHECKING_ACCOUNT);
                    String fullName = resultSet.getString(ColumnName.USER_OPERATION_FULL_NAME);
                    UserOperation userOperation = new UserOperation
                            .Builder()
                            .addId(id)
                            .addTimestamp(timestamp)
                            .addRate(rate)
                            .addSum(sum)
                            .addCurrencyId(currencyId)
                            .addUserId(userId)
                            .addDutiesId(dutiesId)
                            .addOperationId(operationId)
                            .addSpecification(specification)
                            .addCheckingAccount(checkingAccount)
                            .addFullName(fullName)
                            .build();
                    userOperations.add(userOperation);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all userOperation", e);
            throw new DAOException("Database exception during fiend all userOperation", e);
        }
        return userOperations;
    }
}
