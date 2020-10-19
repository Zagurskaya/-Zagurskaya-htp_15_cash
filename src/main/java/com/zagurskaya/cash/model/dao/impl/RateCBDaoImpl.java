package com.zagurskaya.cash.model.dao.impl;

import com.zagurskaya.cash.entity.RateCB;
import com.zagurskaya.cash.exception.DAOException;
import com.zagurskaya.cash.exception.RepositoryConstraintViolationException;
import com.zagurskaya.cash.model.dao.AbstractDao;
import com.zagurskaya.cash.model.dao.RateCBDao;
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

public class RateCBDaoImpl extends AbstractDao implements RateCBDao {

    private static final Logger logger = LogManager.getLogger(RateCBDaoImpl.class);

    private static final String SQL_SELECT_ALL_RATECBS = "SELECT id, coming, spending, timestamp, sum, isBack FROM `rateCB`  ORDER BY id LIMIT ? Offset ? ";
    private static final String SQL_SELECT_RATECB_BY_ID = "SELECT id, coming, spending, timestamp, sum, isBack FROM `rateCB` WHERE id= ? ";
    private static final String SQL_INSERT_RATECB = "INSERT INTO rateCB(coming, spending, timestamp, sum, isBack) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_RATECB = "UPDATE rateCB SET coming=?, spending = ?, timestamp = ?, sum = ?, isBack = ? WHERE id= ?";
    private static final String SQL_DELETE_RATECB = "DELETE FROM rateCB WHERE id=?";
    private static final String SQL_SELECT_COUNT_RATECBS = "SELECT COUNT(id) FROM `rateCB`";

    /**
     * Получение списка валют КБ начиная с startPosition позиции в количестве <= limit
     *
     * @param limit         - количество
     * @param startPosition - начальная позиция
     * @return список валют
     */
    @Override
    public List<RateCB> findAll(int limit, int startPosition) throws DAOException {
        List<RateCB> rateCBs = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_RATECBS)) {
                preparedStatement.setLong(1, limit);
                preparedStatement.setLong(2, startPosition);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.RATECB_ID);
                    Long coming = resultSet.getLong(ColumnName.RATECB_COMING);
                    Long spending = resultSet.getLong(ColumnName.RATECB_SPENDING);
                    Timestamp timestamp = resultSet.getTimestamp(ColumnName.RATECB_TIMESTAMP);
                    Double sum = resultSet.getDouble(ColumnName.RATECB_SUM);
                    boolean isBack = resultSet.getBoolean(ColumnName.RATECB_IS_BACK);
                    RateCB rateCB = new RateCB
                            .Builder()
                            .addId(id)
                            .addСoming(coming)
                            .addSpending(spending)
                            .addTimestamp(timestamp)
                            .addSum(sum)
                            .addIsBack(isBack)
                            .build();
                    rateCBs.add(rateCB);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all rateCB", e);
            throw new DAOException("Database exception during fiend all rateCB", e);
        }
        return rateCBs;
    }

    /**
     * Поиск валюты КБ по ID
     *
     * @param id - ID
     * @return валюта КБ
     */
    @Override
    public RateCB findById(Long id) throws DAOException {
        RateCB rateCB = null;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_RATECB_BY_ID)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long coming = resultSet.getLong(ColumnName.RATECB_COMING);
                    Long spending = resultSet.getLong(ColumnName.RATECB_SPENDING);
                    Timestamp timestamp = resultSet.getTimestamp(ColumnName.RATECB_TIMESTAMP);
                    Double sum = resultSet.getDouble(ColumnName.RATECB_SUM);
                    boolean isBack = resultSet.getBoolean(ColumnName.RATECB_IS_BACK);
                    rateCB = new RateCB
                            .Builder()
                            .addId(id)
                            .addСoming(coming)
                            .addSpending(spending)
                            .addTimestamp(timestamp)
                            .addSum(sum)
                            .addIsBack(isBack)
                            .build();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend rateCB by id", e);
            throw new DAOException("Database exception during fiend rateCB by id", e);
        }
        return rateCB;
    }

    /**
     * Создание валюты КБ
     *
     * @param rateCB - валюта КБ
     * @return true при успешном создании
     */
    @Override
    public Long create(RateCB rateCB) throws DAOException, RepositoryConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_RATECB, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, rateCB.getComing());
                preparedStatement.setLong(2, rateCB.getSpending());
                preparedStatement.setTimestamp(3, rateCB.getTimestamp());
                preparedStatement.setDouble(4, rateCB.getSum());
                preparedStatement.setBoolean(5, rateCB.getIsBack());
                result = preparedStatement.executeUpdate();
                if (1 == result) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RepositoryConstraintViolationException("Duplicate data rateCB", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during create rateCB", e);
            throw new DAOException("Database exception during create rateCB", e);
        }
        return 0L;
    }

    /**
     * Изменение валюты КБ
     *
     * @param rateCB - валюта КБ
     * @return true при успешном изменении
     */
    @Override
    public boolean update(RateCB rateCB) throws DAOException, RepositoryConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_RATECB)) {
                preparedStatement.setLong(1, rateCB.getComing());
                preparedStatement.setLong(2, rateCB.getSpending());
                preparedStatement.setTimestamp(3, rateCB.getTimestamp());
                preparedStatement.setDouble(4, rateCB.getSum());
                preparedStatement.setBoolean(5, rateCB.getIsBack());
                preparedStatement.setLong(6, rateCB.getId());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RepositoryConstraintViolationException("Duplicate data rateCB", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during update rateCB", e);
            throw new DAOException("Database exception during update rateCB ", e);
        }
        return 1 == result;
    }

    /**
     * Удаление валюты КБ
     *
     * @param rateCB - валюта КБ
     * @return true при успешном удаление
     */
    @Override
    public boolean delete(RateCB rateCB) throws DAOException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_RATECB)) {
                preparedStatement.setLong(1, rateCB.getId());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during delete rateCB", e);
            throw new DAOException("Database exception during delete rateCB ", e);
        }
        return 1 == result;
    }

    /**
     * Количество строк в таблите валюты КБ
     *
     * @return количество строк
     * @throws DAOException ошибке доступа к базе данных или других ошибках.
     */
    @Override
    public Long countRows() throws DAOException {
        Long count;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COUNT_RATECBS)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                count = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend count currency row", e);
            throw new DAOException("Database exception during fiend count currency row", e);
        }
        return count;
    }
}
