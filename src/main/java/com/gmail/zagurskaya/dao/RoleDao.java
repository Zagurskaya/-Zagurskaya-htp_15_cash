package com.gmail.zagurskaya.dao;

import com.gmail.zagurskaya.beans.Role;
import com.gmail.zagurskaya.connection.ConnCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends AbstractDao implements Dao<Role> {
    @Override
    public boolean create(Role role) throws SQLException {
        String sql = String.format(
                "INSERT INTO `roles`(`id`, `name`) VALUES ('%d','%s')",
                role.getId(), role.getName());
        role.setId(executeCreate(sql));
        return role.getId() > 0;
    }

    @Override
    public Role read(long id) throws SQLException {
        List<Role> role = getAll(" WHERE id=" + id);
        return role.size() == 0 ? null : role.get(0);
    }

    @Override
    public boolean update(Role role) throws SQLException {
        String sql = String.format(
                " UPDATE `roles` SET `name`='%s' WHERE `id`='%d'",
                role.getName(),
                role.getId());
        return executeUpdate(sql);
    }

    @Override
    public boolean delete(Role role) throws SQLException {
        String sql = String.format(
                "DELETE FROM `roles` WHERE `id`='%d'",
                role.getId());
        return executeUpdate(sql);
    }

    @Override
    public List<Role> getAll() throws SQLException {
        return getAll("");
    }

    @Override
    public List<Role> getAll(String where) throws SQLException {
        List<Role> result = new ArrayList<>();
        try (Connection connection = ConnCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "SELECT * FROM `roles` " + where);
//            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong("id"));
                role.setName(resultSet.getString("name"));
                result.add(role);
            }
        }
        return result;
    }

}
