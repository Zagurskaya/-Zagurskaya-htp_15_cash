package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class CurrencyServiceIntegrationTest extends Assert {

    private CurrencyService currencyService;

    @BeforeTest
    public void setUp() {
        currencyService = new CurrencyServiceImpl();
    }

    @Test(priority = 1)
    public void findByIdTest() throws ServiceException, DaoException {
        Long id = AttributeName.NС;
        Currency currency = new Currency
                .Builder()
                .addId(id)
                .addIso("BUR")
                .addNameEN("Belorussian ruble")
                .addNameRU("Белорусский рубль")
                .build();
        Currency actual = currencyService.findById(id);
        assertEquals(actual, currency);
    }

    @Test(priority = 2)
    public void createTest() throws ServiceException, CommandException {
        List<Currency> currencyListBefore = currencyService.findAll();
        Currency currency = new Currency
                .Builder()
                .addId(998L)
                .addIso("TEST")
                .addNameEN("Test ruble")
                .addNameRU("Тест рубль")
                .build();
        currencyService.create(currency);
        List<Currency> currencyListAfter = currencyService.findAll();
        assertEquals(currencyListAfter.size(), currencyListBefore.size()+1);
    }

    @Test(priority = 3)
    public void updateTest() throws ServiceException, CommandException {
        List<Currency> currencyList = currencyService.findAll();
        Long id = currencyList.get(currencyList.size() - 1).getId();
        Currency currency = currencyService.findById(id);
        Currency updateCurrency = new Currency
                .Builder()
                .addId(currency.getId())
                .addIso(currency.getIso() + "1")
                .addNameEN(currency.getNameEN() + "1")
                .addNameRU(currency.getNameRU() + "1")
                .build();
        assertTrue(currencyService.update(updateCurrency));
        Currency currencyAfter = currencyService.findById(id);
        assertEquals(currencyAfter, updateCurrency);
    }

    @Test(priority = 4)
    public void deleteTest() throws ServiceException {
        List<Currency> currencyListBefore = currencyService.findAll();
        Currency currency = currencyListBefore.get(currencyListBefore.size() - 1);
        assertTrue(currencyService.delete(currency));
        List<Currency> currencyListAfter = currencyService.findAll();
        assertEquals(currencyListBefore.size() - 1, currencyListAfter.size());
    }

    @Test(priority = 5)
    public void countRowsTest() throws ServiceException {
        List<Currency> currencyLis = currencyService.findAll();
        int actual = currencyService.countRows();
        assertEquals(actual, currencyLis.size());
    }

    @Test(priority = 6)
    public void onePartOfListOnPage1Test() throws ServiceException {
        List<Currency> currencyLis = currencyService.findAll();
        int expected = currencyLis.size() < AttributeName.RECORDS_PER_PAGE ? currencyLis.size() : AttributeName.RECORDS_PER_PAGE;
        int actual = currencyService.onePartOfListOnPage(1).size();
        assertEquals(actual, expected);
    }
}
