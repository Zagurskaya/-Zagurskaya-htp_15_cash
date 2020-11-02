package com.zagurskaya.cash.controller.command;

public interface PDFDocument {

    void createCheckEn(Long operationId);
    void createCheckRu(Long operationId);

}
