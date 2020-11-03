package com.zagurskaya.cash.controller.command;

public interface PDFDocument {

    void createCheck(Long operationId, int operationNumber, String locale);

}
