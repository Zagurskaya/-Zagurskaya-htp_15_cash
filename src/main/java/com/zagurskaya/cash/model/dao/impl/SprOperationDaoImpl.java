package com.zagurskaya.cash.model.dao.impl;

import com.zagurskaya.cash.entity.SprOperation;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;
import com.zagurskaya.cash.model.dao.AbstractDao;
import com.zagurskaya.cash.model.dao.SprOperationDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class SprOperationDaoImpl extends AbstractDao implements SprOperationDao {

    private static final Logger logger = LogManager.getLogger(SprOperationDaoImpl.class);

    private static final String SQL_SELECT_ALL_SPR_OPERATION_PAGE = "SELECT id, nameRU, nameEN, specification FROM `sprOperation`  ORDER BY id LIMIT ? Offset ? ";
    private static final String SQL_SELECT_ALL_SPR_OPERATION = "SELECT id, nameRU, nameEN, specification FROM `sprOperation` ";
    private static final String SQL_SELECT_SPR_OPERATION_BY_ID = "SELECT id, nameRU, nameEN, specification FROM `sprOperation` WHERE id= ? ";
    private static final String SQL_INSERT_SPR_OPERATION = "INSERT INTO sprOperation(nameRU, nameEN, specification) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_SPR_OPERATION = "UPDATE sprOperation SET nameRU = ?, nameEN ?, specification = ? WHERE id= ?";
    private static final String SQL_DELETE_SPR_OPERATION = "DELETE FROM sprOperation WHERE id=?";
    private static final String SQL_SELECT_COUNT_SPR_OPERATION = "SELECT COUNT(id) FROM `sprOperation`";

    @Override
    public List<SprOperation> findAll(int limit, int startPosition) throws DaoException {
        List<SprOperation> sprOperations = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_SPR_OPERATION_PAGE)) {
                preparedStatement.setLong(1, limit);
                preparedStatement.setLong(2, startPosition);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.SPR_OPERATION_ID);
                    String nameRU = resultSet.getString(ColumnName.SPR_OPERATION_NAME_RU);
                    String nameEN = resultSet.getString(ColumnName.SPR_OPERATION_NAME_EN);
                    String specification = resultSet.getString(ColumnName.SPR_OPERATION_SPECIFICATION);
                    SprOperation sprOperation = new SprOperation.Builder()
                            .addId(id)
                            .addNameRU(nameRU)
                            .addNameEN(nameEN)
                            .addSpecification(specification)
                            .build();
                    sprOperations.add(sprOperation);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all sprOperation", e);
            throw new DaoException("Database exception during fiend all sprOperation", e);
        }
        return sprOperations;
    }

    @Override
    public SprOperation findById(Long id) throws DaoException {
        SprOperation sprOperation = null;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_SPR_OPERATION_BY_ID)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String nameRU = resultSet.getString(ColumnName.SPR_OPERATION_NAME_RU);
                    String nameEN = resultSet.getString(ColumnName.SPR_OPERATION_NAME_EN);
                    String specification = resultSet.getString(ColumnName.SPR_OPERATION_SPECIFICATION);
                    sprOperation = new SprOperation.Builder()
                            .addId(id)
                            .addId(id)
                            .addNameRU(nameRU)
                            .addNameEN(nameEN)
                            .addSpecification(specification)
                            .build();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend sprOperation by id", e);
            throw new DaoException("Database exception during fiend sprOperation by id", e);
        }
        return sprOperation;
    }

    @Override
    public Long create(SprOperation sprOperation) throws DaoException, DaoConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_SPR_OPERATION)) {
                preparedStatement.setLong(1, sprOperation.getId());
                preparedStatement.setString(2, sprOperation.getNameRU());
                preparedStatement.setString(3, sprOperation.getNameEN());
                preparedStatement.setString(4, sprOperation.getSpecification());
                result = preparedStatement.executeUpdate();
                if (1 == result) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DaoConstraintViolationException("Duplicate data sprOperation", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during createCheckEn sprOperation", e);
            throw new DaoException("Database exception during createCheckEn sprOperation", e);
        }
        return 0L;
    }

    @Override
    public boolean update(SprOperation sprOperation) throws DaoException, DaoConstraintViolationException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_SPR_OPERATION)) {
                preparedStatement.setString(1, sprOperation.getNameRU());
                preparedStatement.setString(2, sprOperation.getNameEN());
                preparedStatement.setString(3, sprOperation.getSpecification());
                preparedStatement.setLong(4, sprOperation.getId());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DaoConstraintViolationException("Duplicate data sprOperation", e);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during update sprOperation", e);
            throw new DaoException("Database exception during update sprOperation ", e);
        }
        return 1 == result;
    }

    @Override
    public boolean delete(SprOperation sprOperation) throws DaoException {
        int result;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_SPR_OPERATION)) {
                preparedStatement.setLong(1, sprOperation.getId());
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during delete sprOperation", e);
            throw new DaoException("Database exception during delete sprOperation ", e);
        }
        return 1 == result;
    }

    @Override
    public int countRows() throws DaoException {
        int count;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COUNT_SPR_OPERATION)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend count sprOperations row", e);
            throw new DaoException("Database exception during fiend count sprOperations row", e);
        }
        return count;
    }

    @Override
    public List<SprOperation> findAll() throws DaoException {
        List<SprOperation> sprOperations = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_SPR_OPERATION)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong(ColumnName.SPR_OPERATION_ID);
                    String nameRU = resultSet.getString(ColumnName.SPR_OPERATION_NAME_RU);
                    String nameEN = resultSet.getString(ColumnName.SPR_OPERATION_NAME_EN);
                    String specification = resultSet.getString(ColumnName.SPR_OPERATION_SPECIFICATION);
                    SprOperation sprOperation = new SprOperation.Builder()
                            .addId(id)
                            .addNameRU(nameRU)
                            .addNameEN(nameEN)
                            .addSpecification(specification)
                            .build();
                    sprOperations.add(sprOperation);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Database exception during fiend all sprOperation", e);
            throw new DaoException("Database exception during fiend all sprOperation", e);
        }
        return sprOperations;
    }
}
