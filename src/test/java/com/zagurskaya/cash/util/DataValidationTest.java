package com.zagurskaya.cash.util;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.exception.CommandException;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;

public class DataValidationTest extends Assert {

    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    @Test
    public void isCreateUpdateDeleteOperationTest() {
        String post = "post";
        Mockito.when(request.getMethod()).thenReturn(post);
        assertTrue(DataValidation.isCreateUpdateDeleteOperation(request));
    }

    @Test
    public void isSaveOperationTest() {
        String save = "save";
        Mockito.when(request.getParameter(save)).thenReturn(save);
        assertTrue(DataValidation.isSaveOperation(request));
    }

    @Test
    public void isUpdateOperationTest() {
        String update = "update";
        Mockito.when(request.getParameter(update)).thenReturn(update);
        assertTrue(DataValidation.isUpdateOperation(request));
    }

    @Test
    public void isDeleteOperationTest() {
        String delete = "delete";
        Mockito.when(request.getParameter(delete)).thenReturn(delete);
        assertTrue(DataValidation.isDeleteOperation(request));
    }

    @Test
    public void isOpenOperationTest() {
        String open = "open";
        Mockito.when(request.getParameter(open)).thenReturn(open);
        assertTrue(DataValidation.isOpenOperation(request));
    }

    @Test
    public void isCloseOperationTest() {
        String close = "close";
        Mockito.when(request.getParameter(close)).thenReturn(close);
        assertTrue(DataValidation.isCloseOperation(request));
    }

    @Test
    public void isContinueOperationTest() {
        String enter = "enter";
        Mockito.when(request.getParameter(enter)).thenReturn(enter);
        assertTrue(DataValidation.isContinueOperation(request));
    }

    @Test
    public void isGetBalanceOperationTest() {
        String balance = "balance";
        Mockito.when(request.getParameter(balance)).thenReturn(balance);
        assertTrue(DataValidation.isGetBalanceOperation(request));
    }

    @Test(expectedExceptions = CommandException.class)
    public void isUserLengthFieldsValidTestThrowsCommandExceptionByLogin() throws CommandException {
        Mockito.when(request.getParameter(AttributeName.LOGIN)).thenReturn("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        Mockito.when(request.getParameter(AttributeName.PASSWORD)).thenReturn("admin");
        Mockito.when(request.getParameter(AttributeName.FULL_MANE)).thenReturn("admin");
        Mockito.when(request.getParameter(AttributeName.ROLE)).thenReturn("admin");
        DataValidation.isUserLengthFieldsValid(request);
    }

    @Test(expectedExceptions = CommandException.class)
    public void isUserLengthFieldsValidTestThrowsCommandExceptionByPassword() throws CommandException {
        Mockito.when(request.getParameter(AttributeName.LOGIN)).thenReturn("admin");
        Mockito.when(request.getParameter(AttributeName.PASSWORD)).thenReturn("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        Mockito.when(request.getParameter(AttributeName.FULL_MANE)).thenReturn("admin");
        Mockito.when(request.getParameter(AttributeName.ROLE)).thenReturn("admin");
        DataValidation.isUserLengthFieldsValid(request);
    }

    @Test(expectedExceptions = CommandException.class)
    public void isUserLengthFieldsValidTestThrowsCommandExceptionByFullName() throws CommandException {
        Mockito.when(request.getParameter(AttributeName.LOGIN)).thenReturn("admin");
        Mockito.when(request.getParameter(AttributeName.PASSWORD)).thenReturn("admin");
        Mockito.when(request.getParameter(AttributeName.FULL_MANE)).thenReturn("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        Mockito.when(request.getParameter(AttributeName.ROLE)).thenReturn("admin");
        DataValidation.isUserLengthFieldsValid(request);
    }

    @Test(expectedExceptions = CommandException.class)
    public void isUserLengthFieldsValidTestThrowsCommandExceptionByRole() throws CommandException {
        Mockito.when(request.getParameter(AttributeName.LOGIN)).thenReturn("admin");
        Mockito.when(request.getParameter(AttributeName.PASSWORD)).thenReturn("admin");
        Mockito.when(request.getParameter(AttributeName.FULL_MANE)).thenReturn("admin");
        Mockito.when(request.getParameter(AttributeName.ROLE)).thenReturn("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        DataValidation.isUserLengthFieldsValid(request);
    }
}
