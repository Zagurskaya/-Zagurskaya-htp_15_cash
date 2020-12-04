package com.zagurskaya.cash.util;

import com.zagurskaya.cash.exception.CommandException;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DataUtilTest extends Assert {

    private HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    @Test
    public void getStringTest() throws CommandException {
        String expected = "asd123_-";
        String actual = DataUtil.getString("asd123_-", RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_VALIDATE_PATTERN);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CommandException.class)
    public void getStringTestThrowCommandException() throws CommandException {
        DataUtil.getString("asd123_-//", RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_VALIDATE_PATTERN);
    }

    @Test
    public void getLongTest() {
        String name = "id";
        String number = "10";
        Mockito.when(req.getParameter(name)).thenReturn(number);
        Long expected = 10L;
        Long actual = DataUtil.getLong(req, name);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void getLongTestThrowCommandException() {
        String name = "id";
        String number = "10a";
        Mockito.when(req.getParameter(name)).thenReturn(number);
        DataUtil.getLong(req, name);
    }

    @Test
    public void getFormattedLocalDateStartDateTimeTest() {
        LocalDate localDate = LocalDate.of(2020, 2, 2);
        String expected = "2020-02-02 00:00:00.000";
        String actual = DataUtil.getFormattedLocalDateStartDateTime(localDate);
        assertEquals(actual, expected);
    }

    @Test
    public void getFormattedCheckTest() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 2, 2, 12, 1, 1);
        String expected = "2020.02.02 12:01:01";
        String actual = DataUtil.getFormattedCheck(localDateTime);
        assertEquals(actual, expected);
    }

    @Test
    public void getFormattedLocalDateTimeTest() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 2, 2, 12, 1, 1);
        String expected = "20200202120101";
        String actual = DataUtil.getFormattedLocalDateTime(localDateTime);
        assertEquals(actual, expected);
    }

    @Test
    public void getFormattedStringToLocalDateTest() {
        String localDate = "20200202";
        LocalDate expected = LocalDate.of(2020, 2, 2);
        LocalDate actual = DataUtil.getFormattedStringToLocalDate(localDate);
        assertEquals(actual, expected);
    }

    @Test
    public void getFormattedStringToLocalDateTimeTest() {
        String localDate = "2020-02-02 12:01:01";
        LocalDateTime expected = LocalDateTime.of(2020, 2, 2, 12, 1, 1);
        LocalDateTime actual = DataUtil.getFormattedStringToLocalDateTime(localDate);
        assertEquals(actual, expected);
    }

    @Test
    public void getFormattedLocalDateToStringTest() {
        LocalDate localDate = LocalDate.of(2020, 2, 2);
        String expected = "20200202";
        String actual = DataUtil.getFormattedLocalDateToString(localDate);
        assertEquals(actual, expected);
    }
}
