package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.impl.DutiesServiceImpl;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DutiesServiceIntegrationTest extends Assert {

    private DutiesService dutiesService;
    private UserService userService;

    @BeforeTest
    public void setUp() {
        dutiesService = new DutiesServiceImpl();
        userService = new UserServiceImpl();
    }

    @Test(priority = 1)
    public void findByIdTest() throws ServiceException, DaoException {
        Duties actual = dutiesService.findById(1L);
        assertNotNull(actual);
    }

    @Test(priority = 2)
    public void openDutiesUserTodayTest() throws ServiceException{
        int countRowsBefore = dutiesService.countRows();
        User user = userService.findById(2L);
        dutiesService.openDutiesUserToday(user, LocalDate.now().toString());
        int countRowsAfter = dutiesService.countRows();
        assertEquals(countRowsAfter, countRowsBefore + 1);
    }

    @Test(priority = 3)
    public void updateTest() throws ServiceException, CommandException {
        int numberOfPages = (int) Math.ceil(dutiesService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
        int countRowsBefore = dutiesService.countRows();

        List<Duties> dutiesList = dutiesService.onePartOfListOnPage(numberOfPages);
        Long id = dutiesList.get(dutiesList.size() - 1).getId();
        Duties duties = dutiesService.findById(id);
        Duties updateDuties = new Duties
                .Builder()
                .addId(duties.getId())
                .addUserId(duties.getUserId())
                .addLocalDateTime(LocalDateTime.now())
                .addIsClose(false)
                .build();
        assertTrue(dutiesService.update(updateDuties));
        Duties dutiesAfter = dutiesService.findById(id);
        assertEquals(dutiesAfter.getId(), updateDuties.getId());
        assertNotEquals(dutiesAfter.getLocalDateTime().toString(), updateDuties.getLocalDateTime().toString());
        assertEquals(dutiesAfter.getIsClose(), updateDuties.getIsClose());
        int countRowsAfter = dutiesService.countRows();
        assertEquals(countRowsAfter, countRowsBefore);
    }

    @Test(priority = 4)
    public void closeOpenDutiesUserTodayTest() throws ServiceException {
        int numberOfPages = (int) Math.ceil(dutiesService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
        int countRowsBefore = dutiesService.countRows();

        List<Duties> dutiesList = dutiesService.onePartOfListOnPage(numberOfPages);
        Long id = dutiesList.get(dutiesList.size() - 1).getId();
        Long userId = dutiesList.get(dutiesList.size() - 1).getUserId();
        User user = userService.findById(userId);
        Duties dutiesBefore = dutiesService.findById(id);

        dutiesService.closeOpenDutiesUserToday(user);

        Duties dutiesAfter = dutiesService.findById(id);
        assertEquals(dutiesAfter.getId(), dutiesBefore.getId());
        assertEquals(dutiesAfter.getLocalDateTime().toString(), dutiesBefore.getLocalDateTime().toString());
        assertNotEquals(dutiesAfter.getIsClose(), dutiesBefore.getIsClose());
        int countRowsAfter = dutiesService.countRows();
        assertEquals(countRowsAfter, countRowsBefore);
    }

    @Test(priority = 5)
    public void deleteTest() throws ServiceException {
        int numberOfPages = (int) Math.ceil(dutiesService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
        int countRowsBefore = dutiesService.countRows();
        List<Duties> dutiesList = dutiesService.onePartOfListOnPage(numberOfPages);
        Long id = dutiesList.get(dutiesList.size() - 1).getId();
        Duties duties = dutiesService.findById(id);
        assertTrue(dutiesService.delete(duties));
        int countRowsAfter = dutiesService.countRows();
        assertEquals(countRowsBefore - 1, countRowsAfter);
    }

    @Test(priority = 6)
    public void countRowsTest() throws ServiceException {
        int numberOfPages = (int) Math.ceil(dutiesService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
        List<Duties> dutiesLis = dutiesService.onePartOfListOnPage(numberOfPages);
        int size = (numberOfPages - 1) * AttributeName.RECORDS_PER_PAGE + dutiesLis.size();
        int actual = dutiesService.countRows();
        assertEquals(actual, size);
    }

    @Test(priority = 7)
    public void onePartOfListOnPage1Test() throws ServiceException {
        int dutiesLisSize = dutiesService.countRows();
        int expected = dutiesLisSize < AttributeName.RECORDS_PER_PAGE ?
                dutiesLisSize % AttributeName.RECORDS_PER_PAGE : AttributeName.RECORDS_PER_PAGE;
        int actual = dutiesService.onePartOfListOnPage(1).size();
        assertEquals(actual, expected);
    }
}
