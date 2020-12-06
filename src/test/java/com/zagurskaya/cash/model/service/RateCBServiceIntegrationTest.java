package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.RateCB;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.impl.RateCBServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RateCBServiceIntegrationTest extends Assert {

    private RateCBService rateCBService;

    @BeforeTest
    public void setUp() {
        rateCBService = new RateCBServiceImpl();
    }

    @Test(priority = 1)
    public void findByIdTest() throws ServiceException, DaoException {
        Long id = 1L;
        RateCB actual = rateCBService.findById(id);
        assertNotNull(actual);
    }

    @Test(priority = 2)
    public void createTest() throws ServiceException, CommandException {
        int countRowsBefore = rateCBService.countRows();
        RateCB rateCB = new RateCB
                .Builder()
                .addСoming(AttributeName.NС)
                .addSpending(840L)
                .addLocalDateTime(LocalDateTime.now())
                .addSum(new BigDecimal(1))
                .addIsBack(true)
                .build();
        assertTrue(rateCBService.create(rateCB));
        int countRowsAfter = rateCBService.countRows();
        assertEquals(countRowsAfter, countRowsBefore + 1);
    }

    @Test(priority = 3)
    public void createListTest() throws ServiceException, CommandException {
        int countRowsBefore = rateCBService.countRows();
        RateCB rateCB1 = new RateCB
                .Builder()
                .addСoming(978L)
                .addSpending(840L)
                .addLocalDateTime(LocalDateTime.now())
                .addSum(new BigDecimal(1))
                .addIsBack(true)
                .build();
        RateCB rateCB2 = new RateCB
                .Builder()
                .addСoming(840L)
                .addSpending(978L)
                .addLocalDateTime(LocalDateTime.now())
                .addSum(new BigDecimal(1))
                .addIsBack(true)
                .build();
        List<RateCB> list = new ArrayList<>();
        list.add(rateCB1);
        list.add(rateCB2);
        rateCBService.create(list);
        int countRowsAfter = rateCBService.countRows();
        assertEquals(countRowsAfter, countRowsBefore + 2);
    }

    @Test(priority = 4)
    public void updateTest() throws ServiceException, CommandException {
        int numberOfPages = (int) Math.ceil(rateCBService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
        int countRowsBefore = rateCBService.countRows();

        List<RateCB> rateCBList = rateCBService.onePartOfListOnPage(numberOfPages);
        Long id = rateCBList.get(rateCBList.size() - 1).getId();
        RateCB rateCB = rateCBService.findById(id);
        RateCB updateRateCB = new RateCB
                .Builder()
                .addId(rateCB.getId())
                .addСoming(rateCB.getComing())
                .addSpending(rateCB.getSpending())
                .addLocalDateTime(rateCB.getLocalDateTime())
                .addSum(new BigDecimal(2))
                .addIsBack(true)
                .build();
        assertTrue(rateCBService.update(updateRateCB));
        RateCB rateCBAfter = rateCBService.findById(id);
        assertEquals(rateCBAfter.getId(), updateRateCB.getId());
        assertEquals(rateCBAfter.getComing(), updateRateCB.getComing());
        assertEquals(rateCBAfter.getSpending(), updateRateCB.getSpending());
        assertEquals(rateCBAfter.getLocalDateTime(), updateRateCB.getLocalDateTime());
        assertTrue(rateCBAfter.getSum().subtract(updateRateCB.getSum()).compareTo(new BigDecimal("0.00")) == 0);
        int countRowsAfter = rateCBService.countRows();
        assertEquals(countRowsAfter, countRowsBefore);
    }

    @Test(priority = 5)
    public void deleteTest() throws ServiceException {
        int numberOfPages = (int) Math.ceil(rateCBService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
        int countRowsBefore = rateCBService.countRows();
        List<RateCB> rateCBList = rateCBService.onePartOfListOnPage(numberOfPages);
        Long id = rateCBList.get(rateCBList.size() - 1).getId();
        RateCB rateCB = rateCBService.findById(id);
        assertTrue(rateCBService.delete(rateCB));
        int countRowsAfter = rateCBService.countRows();
        assertEquals(countRowsBefore - 1, countRowsAfter);
    }

    @Test(priority = 6)
    public void countRowsTest() throws ServiceException {
        int numberOfPages = (int) Math.ceil(rateCBService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
        List<RateCB> rateCBLis = rateCBService.onePartOfListOnPage(numberOfPages);
        int size = (numberOfPages - 1) * AttributeName.RECORDS_PER_PAGE + rateCBLis.size();
        int actual = rateCBService.countRows();
        assertEquals(actual, size);
    }

    @Test(priority = 7)
    public void onePartOfListOnPage1Test() throws ServiceException {
        int rateCBLisSize = rateCBService.countRows();
        int expected = rateCBLisSize < AttributeName.RECORDS_PER_PAGE ?
                rateCBLisSize % AttributeName.RECORDS_PER_PAGE : AttributeName.RECORDS_PER_PAGE;
        int actual = rateCBService.onePartOfListOnPage(1).size();
        assertEquals(actual, expected);
    }
}
