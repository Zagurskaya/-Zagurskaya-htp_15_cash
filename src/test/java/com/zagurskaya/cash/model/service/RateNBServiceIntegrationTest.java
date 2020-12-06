package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.RateNB;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.impl.RateNBServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RateNBServiceIntegrationTest extends Assert {

    private RateNBService rateNBService;

    @BeforeTest
    public void setUp() {
        rateNBService = new RateNBServiceImpl();
    }

    @Test(priority = 1)
    public void findByIdTest() throws ServiceException, DaoException {
        Long id = 1L;
        RateNB actual = rateNBService.findById(id);
        assertNotNull(actual);
    }

    @Test(priority = 2)
    public void createTest() throws ServiceException, CommandException {
        int countRowsBefore = rateNBService.countRows();
        RateNB rateNB = new RateNB
                .Builder()
                .addCurrencyId(AttributeName.NС)
                .addDate(LocalDate.now())
                .addSum(new BigDecimal(1))
                .build();
        assertTrue(rateNBService.create(rateNB));
        int countRowsAfter = rateNBService.countRows();
        assertEquals(countRowsAfter, countRowsBefore + 1);
    }

    @Test(priority = 3)
    public void updateTest() throws ServiceException, CommandException {
        int numberOfPages = (int) Math.ceil(rateNBService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
        int countRowsBefore = rateNBService.countRows();

        List<RateNB> rateNBList = rateNBService.onePartOfListOnPage(numberOfPages);
        Long id = rateNBList.get(rateNBList.size() - 1).getId();
        RateNB rateNB = rateNBService.findById(id);
        RateNB updateRateNB = new RateNB
                .Builder()
                .addId(rateNB.getId())
                .addCurrencyId(rateNB.getCurrencyId())
                .addDate(rateNB.getDate())
                .addSum(new BigDecimal(2.0))
                .build();
        assertTrue(rateNBService.update(updateRateNB));
        RateNB rateNBAfter = rateNBService.findById(id);
        assertEquals(rateNBAfter.getId(), updateRateNB.getId());
        assertEquals(rateNBAfter.getCurrencyId(), updateRateNB.getCurrencyId());
        assertEquals(rateNBAfter.getDate(), updateRateNB.getDate());
        assertTrue(rateNBAfter.getSum().subtract(updateRateNB.getSum()).compareTo(new BigDecimal("0.00")) == 0);
        int countRowsAfter = rateNBService.countRows();
        assertEquals(countRowsAfter, countRowsBefore);
    }

    @Test(priority = 4)
    public void deleteTest() throws ServiceException {
        int numberOfPages = (int) Math.ceil(rateNBService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
        int countRowsBefore = rateNBService.countRows();
        List<RateNB> rateNBList = rateNBService.onePartOfListOnPage(numberOfPages);
        Long id = rateNBList.get(rateNBList.size() - 1).getId();
        RateNB rateNB = rateNBService.findById(id);
        assertTrue(rateNBService.delete(rateNB));
        int countRowsAfter = rateNBService.countRows();
        assertEquals(countRowsBefore - 1, countRowsAfter);
    }

    @Test(priority = 5)
    public void countRowsTest() throws ServiceException {
        int numberOfPages = (int) Math.ceil(rateNBService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
        List<RateNB> rateNBLis = rateNBService.onePartOfListOnPage(numberOfPages);
        int size = (numberOfPages - 1) * AttributeName.RECORDS_PER_PAGE + rateNBLis.size();
        int actual = rateNBService.countRows();
        assertEquals(actual, size);
    }

    @Test(priority = 6)
    public void onePartOfListOnPage1Test() throws ServiceException {
        int rateNBLisSize = rateNBService.countRows();
        int expected = rateNBLisSize < AttributeName.RECORDS_PER_PAGE ?
                rateNBLisSize % AttributeName.RECORDS_PER_PAGE : AttributeName.RECORDS_PER_PAGE;
        int actual = rateNBService.onePartOfListOnPage(1).size();
        assertEquals(actual, expected);
    }
}
