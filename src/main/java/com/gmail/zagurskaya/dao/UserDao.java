package com.gmail.zagurskaya.dao;

import com.gmail.zagurskaya.beans.User;
import com.gmail.zagurskaya.connection.ConnCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao implements Dao<User> {
    @Override
    public boolean create(User user) throws SQLException {
        String sql = String.format(
                "INSERT INTO `users`(`login`, `password`, `roleId`)" +
                        "VALUES ('%s','%s','%d')",
                user.getLogin(), user.getPassword(), user.getRoleId());
        user.setId(executeCreate(sql));
        return user.getId() > 0;
    }

    @Override
    public User read(long id) throws SQLException {
        List<User> users = getAll(" WHERE id=" + id);
        return users.size() == 0 ? null : users.get(0);
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql = String.format(
                "UPDATE `users` SET `login`='%s', `password`='%s',`roleId`='%d' WHERE `id`='%d'",
                user.getLogin(), user.getPassword(), user.getRoleId(),
                user.getId());
        return executeUpdate(sql);
    }

    @Override
    public boolean delete(User user) throws SQLException {
        String sql = String.format(
                "DELETE FROM `users` WHERE `id`='%s'",
                user.getId());
        return executeUpdate(sql);
    }

    @Override
    public List<User> getAll() throws SQLException {
        return getAll("");
    }

    @Override
    public List<User> getAll(String where) throws SQLException {
        List<User> result = new ArrayList<>();
        try (Connection connection = ConnCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "SELECT * FROM `users` " + where);
//            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleId(resultSet.getLong("roleId"));
                result.add(user);
            }
        }
        return result;
    }

}
