package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static com.zagurskaya.cash.entity.RoleType.ADMIN;

public class UserServiceIntegrationTest extends Assert {

    private UserService userService;

    @BeforeTest
    public void setUp() {
        userService = new UserServiceImpl();
    }


    @Test(priority = 1)
    public void findUserByLoginAndValidPasswordTest() throws ServiceException {
        String login = "Admin";
        String passwordParam = "Admin";
        User user = new User.Builder()
                .addId(1L)
                .addLogin(login)
                .addFullName("Admin Admin Adminovish")
                .addRole(ADMIN)
                .build();
        User actual = userService.findUserByLoginAndValidPassword(login, passwordParam);
        assertEquals(actual, user);
    }

    @Test(priority = 2)
    public void findByIdTest() throws ServiceException, DaoException {
        Long id = 1L;
        User user = new User.Builder()
                .addId(id)
                .addLogin("Admin")
                .addFullName("Admin Admin Adminovish")
                .addRole(ADMIN)
                .build();
        User actual = userService.findById(id);
        assertEquals(actual, user);
    }

    @Test(priority = 3)
    public void createUserWithPasswordTest() throws ServiceException, CommandException {
        String password = "Admin";
        User user = new User.Builder()
                .addLogin("Admin2")
                .addFullName("Admin Admin Adminovish")
                .addRole(ADMIN)
                .build();
        assertTrue(userService.create(user, password));
    }

    @Test(priority = 4)
    public void updateTest() throws ServiceException, CommandException {
        List<User> userList = userService.findAll();
        Long id = userList.get(userList.size() - 1).getId();
        User user = userService.findById(id);
        User updateUser = new User.Builder()
                .addId(user.getId())
                .addLogin(user.getLogin())
                .addFullName(user.getFullName()+"1")
                .addRole(user.getRole())
                .build();
        assertTrue(userService.update(updateUser));
        User userAfter = userService.findById(id);
        assertEquals(userAfter, updateUser);
    }

    @Test(priority = 5)
    public void deleteTest() throws ServiceException {
        List<User> userListBefore = userService.findAll();
        User user = userListBefore.get(userListBefore.size() - 1);
        assertTrue(userService.delete(user));
        List<User> userListAfter = userService.findAll();
        assertEquals(userListBefore.size() - 1, userListAfter.size());
    }

    @Test(priority = 6)
    public void countRowsTest() throws ServiceException {
        List<User> userLis = userService.findAll();
        int actual = userService.countRows();
        assertEquals(actual, userLis.size());
    }
}
