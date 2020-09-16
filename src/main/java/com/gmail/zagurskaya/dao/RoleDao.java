package com.gmail.zagurskaya.dao;

import com.gmail.zagurskaya.beans.Role;
import com.gmail.zagurskaya.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface RoleDao extends Dao<Role> {
}
