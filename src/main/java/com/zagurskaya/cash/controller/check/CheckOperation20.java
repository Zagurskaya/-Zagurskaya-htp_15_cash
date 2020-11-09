package com.zagurskaya.cash.controller.check;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.PaymentService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import com.zagurskaya.cash.model.service.impl.PaymentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CheckOperation20 extends PDFDocument {
    private static final Logger logger = LogManager.getLogger(CheckOperation20.class);
    private final CurrencyService currencyService = new CurrencyServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();
    private static final String SEPARATOR = "   +--------------------------------------------------+\n";

    public void createCheckEn(Long operationId, Document document, Font font) {
        try {
            UserOperation userOperation = paymentService.findUserOperationById(operationId);
            List<UserEntry> userEntriesList = paymentService.findAllUserEntriesByOperationId(operationId);
            List<Currency> currencies = currencyService.findAll();

            String head = "    OAO TestBank \n" +
                    "    Registration Number N KKS 123456789\n" +
                    "    CASHIER'S CHECK N " + userOperation.getId() + "\n" +
                    "    date " + userOperation.getTimestamp() + "\n" +
                    "    Exchange rate           " + userOperation.getRate() + "\n" +
                    "    SELLING FOREIGN CASH  \n" +
                    "    FOR CASH BYB.RUBLES \n";
            document.add(new Paragraph(head, font));
            String str1 = "";
            String str2 = "";
            String str3 = "";
            String str4 = "";
            for (UserEntry userEntry : userEntriesList) {
                if (!AttributeName.NС.equals(userEntry.getCurrencyId())) {
                    str1 = "    |To be issued to the Client                        |\n";
                    for (Currency currency : currencies) {
                        if (userEntry.getCurrencyId().equals(currency.getId())) {
                            str2 = "    |" + currency.getNameEN() + "    in total    " + userEntry.getSum() + "        |\n";
                        }
                    }
                } else {
                    str3 = "    |Submitted by the Client                           |\n";
                    for (Currency currency : currencies) {
                        if (userEntry.getCurrencyId().equals(currency.getId())) {
                            str4 = "    |" + currency.getNameEN() + "    in total    " + userEntry.getSum() + "        |\n";
                        }
                    }
                }
            }
            String total = SEPARATOR + str1 + SEPARATOR + str2 + SEPARATOR + str3 + SEPARATOR + str4 + SEPARATOR;
            document.add(new Paragraph(total, font));

        } catch (DocumentException | ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

    public void createCheckRu(Long operationId, Document document, Font font) {
        try {
            UserOperation userOperation = paymentService.findUserOperationById(operationId);
            List<UserEntry> userEntriesList = paymentService.findAllUserEntriesByOperationId(operationId);
            List<Currency> currencies = currencyService.findAll();
            String head = "    ОАО ТестБанк \n" +
                    "    Рег. N ГНИ ККС 123456789\n" +
                    "    КАССОВЫЙ  ЧЕК   N " + userOperation.getId() + "\n" +
                    "    дата " + userOperation.getTimestamp() + "\n" +
                    "    ПРОДАЖА НАЛИЧНОЙ ВАЛЮТЫ  \n" +
                    "    ЗА НАЛИЧНЫЕ БЕЛ.РУБЛИ \n" +
                    "    Курс операции           " + userOperation.getRate() + "\n";
            document.add(new Paragraph(head, font));
            String str1 = "";
            String str2 = "";
            String str3 = "";
            String str4 = "";
            for (UserEntry userEntry : userEntriesList) {
                if (!AttributeName.NС.equals(userEntry.getCurrencyId())) {
                    str1 = "    |К выдаче Клиенту                             |\n";
                    for (Currency currency : currencies) {
                        if (userEntry.getCurrencyId().equals(currency.getId())) {
                            str2 = "    |" + currency.getNameRU() + "  в сумме    " + userEntry.getSum() + "|\n";
                        }
                    }
                } else {
                    str3 = "    |Внесено Клиентом                             |\n";
                    for (Currency currency : currencies) {
                        if (userEntry.getCurrencyId().equals(currency.getId())) {
                            str4 = "    |" + currency.getNameRU() + "  в сумме    " + userEntry.getSum() + "|\n";
                        }
                    }
                }
            }
            String total = SEPARATOR + str1 + SEPARATOR + str2 + SEPARATOR + str3 + SEPARATOR + str4 + SEPARATOR;
            document.add(new Paragraph(total, font));
        } catch (DocumentException | ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }
}