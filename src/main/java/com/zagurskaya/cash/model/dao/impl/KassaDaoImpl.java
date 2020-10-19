package com.zagurskaya.cash.model.dao.impl;

import com.zagurskaya.cash.entity.Kassa;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;
import com.zagurskaya.cash.model.dao.AbstractDao;
import com.zagurskaya.cash.model.dao.KassaDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KassaDaoImpl extends AbstractDao implements KassaDao {

    private static final Logger logger = LogManager.getLogger(KassaDaoImpl.class);

    private static final String SQL_SELECT_ALL_KASSA = "SELECT id, currencyId, received, coming, spending, transmitted, balance, userId, date, dutiesId FROM kassa  ORDER BY id LIMIT ? Offset ? ";
    private static final String SQL_SELECT_KASSA_BY_ID = "SELECT id, currencyId, received, coming, spending, transmitted, balance, userId, date, dutiesId FROM kassa WHERE id= ? ";
    private static final String SQL_SELECT_KASSA_BY_CURRENCY_ID_DATE_DUTIES = "SELECT id, currencyId, received, coming, spending, transmitted, balance, userId, date, dutiesId FROM kassa WHERE date = ? AND dutiesId = ? AND currencyId = ? ";
    private static final String SQL_SELECT_KASSA_BY_USER_ID_AND_DUTIES_ID = "SELECT id, currencyId, received, coming, spending, transmitted, balance, userId, date, dutiesId FROM kassa WHERE userId = ? AND dutiesId = ? ";
    private static final String SQL_INSERT_KASSA = "INSERT INTO kassa(currencyId, received, coming, spending, transmitted, balance, userId, date, dutiesId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_KASSA = "UPDATE kassa SET currencyId = ?, received = ?, coming = ?,  spending = ?, transmitted = ?, balance = ?, userId = ?, date = ? , dutiesId = ? WHERE id= ?";
    private static final String SQL_DELETE_KASSA = "DELETE FROM kassa WHERE id=?";
    private static final String SQL_SELECT_COUNT_KASSAS = "SELECT COUNT(id) FROM kassa";

    /**
     * Получение списка записей из картотеки kassa начиная с startPosition позиции в количестве <= limit
     *
     * @param limit         - количество
     * @param startPosition - начальная позиция
     * @return списк записей из картотеки kassa
     */
    @Override
    public List<Kassa> findAll(int limit, int startPosition) throws DaoException {
        List<Kassa> kassaList = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_KASSA)) {
                preparedStatement.setLong(1, limit);
                preparedStatement.setLong(2, startPosition);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.KASSA_ID);
                    Long currencyId = resultSet.getLong(ColumnName.KASSA_CURRENCY_ID);
                    Double received = resultSet.getDouble(ColumnName.KASSA_RESEIVED);
                    Double coming = resultSet.getDouble(ColumnName.KASSA_COMING);
                    Double spending = resultSet.getDouble(ColumnName.KASSA_SPENDING);
                    Double transmitted = resultSet.getDouble(ColumnName.KASSA_TRANSMITTED);
                    Double balance = resultSet.getDouble(ColumnName.KASSA_BALANCE);
                    Long userId = resultSet.getLong(ColumnName.KASSA_USER_ID);
                    Date date = resultSet.getDate(ColumnName.KASSA_DATE);
                    Long dutiesId = resultSet.getLong(ColumnName.KASSA_CURRENCY_ID);
                    Kassa kassa = new Kassa
                            .Builder()
                            .addId(id)
                            .addСurrencyId(currencyId)
                            .addReceived(received)
                            .addСoming(coming)
                            .addSpending(spending)
                            .addTransmitted(transmitted)
                            .addBalance(balance)
                            .addUserId(userId)
                            .addData(date)
                            .addDutiesId(dutiesId)
                            .build();
                    kassaList.add(kassa);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all kassa", e);
            throw new DaoException("Database exception during fiend all kassa", e);
        }
        return kassaList;
    }

    /**
     * Поиск записи из картотеки kassa по ID
     *
     * @param id - ID
     * @return запись из картотеки kassa
     */
    @Override
    public Kassa findById(Long id) throws DaoException {
        Kassa kassa = null;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_KASSA_BY_ID)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long currencyId = resultSet.getLong(ColumnName.KASSA_CURRENCY_ID);
                    Double received = resultSet.getDouble(ColumnName.KASSA_RESEIVED);
                    Double coming = resultSet.getDouble(ColumnName.KASSA_COMING);
                    Double spending = resultSet.getDouble(ColumnName.KASSA_SPENDING);
                    Double transmitted = resultSet.getDouble(ColumnName.KASSA_TRANSMITTED);
                    Double balance = resultSet.getDouble(ColumnName.KASSA_BALANCE);
                    Long userId = resultSet.getLong(ColumnName.KASSA_USER_ID);
                    Date date = resultSet.getDate(ColumnName.KASSA_DATE);
                    Long dutiesId = resultSet.getLong(ColumnName.KASSA_CURRENCY_ID);
                    kassa = new Kassa
                            .Builder()
                            .addId(id)
                            .addСurrencyId(currencyId)
                            .addReceived(received)
                            .addСoming(coming)
                            .addSpending(spending)
                            .addTransmitted(transmitted)
                            .addBalance(balance)
                            .addUserId(userId)
                            .addData(date)
                            .addDutiesId(dutiesId)
                            .build();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend kassa by id", e);
            throw new DaoException("Database exception during fiend kassa by id", e);
        }
        return kassa;
    }

    /**
     * Создание записи в картотеке kassa
     *
     * @param kassa - запись в картотеке kassa
     * @return true при успешном создании
     */
    @Override
    public Long create(Kassa kassa) throws DaoException, DaoConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_KASSA, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, kassa.getCurrencyId());
                preparedStatement.setDouble(2, kassa.getReceived());
                preparedStatement.setDouble(3, kassa.getComing());
                preparedStatement.setDouble(4, kassa.getSpending());
                preparedStatement.setDouble(5, kassa.getTransmitted());
                preparedStatement.setDouble(6, kassa.getBalance());
                preparedStatement.setLong(7, kassa.getUserId());
                preparedStatement.setString(8, kassa.getDate().toString());
                preparedStatement.setLong(9, kassa.getDutiesId());
                result = preparedStatement.executeUpdate();
                if (1 == result) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DaoConstraintViolationException("Duplicate data kassa", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during create kassa", e);
            throw new DaoException("Database exception during create kassa", e);
        }
        return 0L;
    }

    /**
     * Изменение записи в картотеке kassa
     *
     * @param kassa - запись в картотеке kassa
     * @return true при успешном изменении
     */
    @Override
    public boolean update(Kassa kassa) throws DaoException, DaoConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_KASSA)) {
                preparedStatement.setLong(1, kassa.getCurrencyId());
                preparedStatement.setDouble(2, kassa.getReceived());
                preparedStatement.setDouble(3, kassa.getComing());
                preparedStatement.setDouble(4, kassa.getSpending());
                preparedStatement.setDouble(5, kassa.getTransmitted());
                preparedStatement.setDouble(6, kassa.getBalance());
                preparedStatement.setLong(7, kassa.getUserId());
                preparedStatement.setString(8, kassa.getDate().toString());
                preparedStatement.setLong(9, kassa.getDutiesId());
                preparedStatement.setLong(10, kassa.getId());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DaoConstraintViolationException("Duplicate data kassa", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during update kassa", e);
            throw new DaoException("Database exception during update kassa ", e);
        }
        return 1 == result;
    }

    /**
     * Удаление записи в картотеке kassa
     *
     * @param kassa - запись в картотеке kassa
     * @return true при успешном удаление
     */
    @Override
    public boolean delete(Kassa kassa) throws DaoException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_KASSA)) {
                preparedStatement.setLong(1, kassa.getId());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during delete kassa", e);
            throw new DaoException("Database exception during delete kassa ", e);
        }
        return 1 == result;
    }

    /**
     * Количество строк в картотеке kassa
     *
     * @return количество строк
     * @throws DaoException ошибке доступа к базе данных или других ошибках.
     */
    @Override
    public Long countRows() throws DaoException {
        Long count;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COUNT_KASSAS)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                count = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend count kassaList row", e);
            throw new DaoException("Database exception during fiend count kassaList row", e);
        }
        return count;
    }

    @Override
    public Kassa findByCurrencyIdAndDateAndDutiesId(Date date, Long dutiesId, Long currencyId) throws DaoException {
        Kassa kassa = null;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_KASSA_BY_CURRENCY_ID_DATE_DUTIES)) {
                preparedStatement.setString(1, date.toString());
                preparedStatement.setLong(2, dutiesId);
                preparedStatement.setLong(3, currencyId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.KASSA_ID);
                    Double received = resultSet.getDouble(ColumnName.KASSA_RESEIVED);
                    Double coming = resultSet.getDouble(ColumnName.KASSA_COMING);
                    Double spending = resultSet.getDouble(ColumnName.KASSA_SPENDING);
                    Double transmitted = resultSet.getDouble(ColumnName.KASSA_TRANSMITTED);
                    Double balance = resultSet.getDouble(ColumnName.KASSA_BALANCE);
                    Long userId = resultSet.getLong(ColumnName.KASSA_USER_ID);
                    kassa = new Kassa
                            .Builder()
                            .addId(id)
                            .addСurrencyId(currencyId)
                            .addReceived(received)
                            .addСoming(coming)
                            .addSpending(spending)
                            .addTransmitted(transmitted)
                            .addBalance(balance)
                            .addUserId(userId)
                            .addData(date)
                            .addDutiesId(dutiesId)
                            .build();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during findByCurrencyIdAndDateAndDutiesNumber", e);
            throw new DaoException("Database exception during findByCurrencyIdAndDateAndDutiesNumber", e);
        }
        return kassa;
    }

    @Override
    public List<Kassa> findAllByUserIdAndDutiesId(Long userId, Long dutiesId) throws DaoException {
        List<Kassa> kassaList = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_KASSA_BY_USER_ID_AND_DUTIES_ID)) {
                preparedStatement.setLong(1, userId);
                preparedStatement.setLong(2, dutiesId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.KASSA_ID);
                    Long currencyId = resultSet.getLong(ColumnName.KASSA_CURRENCY_ID);
                    Double received = resultSet.getDouble(ColumnName.KASSA_RESEIVED);
                    Double coming = resultSet.getDouble(ColumnName.KASSA_COMING);
                    Double spending = resultSet.getDouble(ColumnName.KASSA_SPENDING);
                    Double transmitted = resultSet.getDouble(ColumnName.KASSA_TRANSMITTED);
                    Double balance = resultSet.getDouble(ColumnName.KASSA_BALANCE);
                    Date date = resultSet.getDate(ColumnName.KASSA_DATE);
                    Kassa kassa = new Kassa
                            .Builder()
                            .addId(id)
                            .addСurrencyId(currencyId)
                            .addReceived(received)
                            .addСoming(coming)
                            .addSpending(spending)
                            .addTransmitted(transmitted)
                            .addBalance(balance)
                            .addUserId(userId)
                            .addData(date)
                            .addDutiesId(dutiesId)
                            .build();
                    kassaList.add(kassa);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during find all by userId and dutiesId ", e);
            throw new DaoException("Database exception during find all by userId and dutiesId ", e);
        }
        return kassaList;
    }
}
