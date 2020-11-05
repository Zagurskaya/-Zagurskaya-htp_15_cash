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

import java.util.List;

public class CheckOperation20 extends PDFDocument {
    private final CurrencyService currencyService = new CurrencyServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();

    public void createCheckEn(Long operationId, Document document, Font font) {
        try {
            UserOperation userOperation = paymentService.findUserOperationById(operationId);
            List<UserEntry> userEntriesList = paymentService.findAllUserEntriesByOperationId(operationId);
            List<Currency> currencies = currencyService.findAll();

            String str1 = "    OAO TestBank \n";
            String str2 = "    Registration Number N KKS 123456789\n";
            String str3 = "    CASHIER'S CHECK N " + userOperation.getId() + "\n";
            String str4 = "    date " + userOperation.getTimestamp() + "\n";
            String str5 = "    Exchange rate           " + userOperation.getRate() + "\n";
            String str6 = "    SELLING FOREIGN CASH  \n";
            String str6_1 = "    FOR CASH BYB.RUBLES \n";
            String total = str1 + str2 + str3 + str4 + str5 + str6 + str6_1;
            document.add(new Paragraph(total, font));
            String str_separator = "   +--------------------------------------------------+\n";
            String str7 = "";
            String str8 = "";
            String str9 = "";
            String str10 = "";
            for (UserEntry userEntry : userEntriesList) {
                if (!AttributeName.NС.equals(userEntry.getCurrencyId())) {
                    str7 = "    |To be issued to the Client                        |\n";
                    for (Currency currency : currencies) {
                        if (userEntry.getCurrencyId().equals(currency.getId())) {
                            str8 = "    |" + currency.getNameEN() + "    in total    " + userEntry.getSum() + "        |\n";
                        }
                    }
                } else {
                    str9 = "    |Submitted by the Client                           |\n";
                    for (Currency currency : currencies) {
                        if (userEntry.getCurrencyId().equals(currency.getId())) {
                            str10 = "    |" + currency.getNameEN() + "    in total    " + userEntry.getSum() + "        |\n";
                        }
                    }
                }
            }
            String total2 = str_separator + str7 + str_separator + str8 + str_separator + str9 + str_separator + str10 + str_separator;
            document.add(new Paragraph(total2, font));

        } catch (DocumentException | ServiceException e) {
            e.printStackTrace();
        }
    }

    public void createCheckRu(Long operationId, Document document, Font font) {
        try {
            UserOperation userOperation = paymentService.findUserOperationById(operationId);
            List<UserEntry> userEntriesList = paymentService.findAllUserEntriesByOperationId(operationId);
            List<Currency> currencies = currencyService.findAll();
            String str1 = "    ОАО ТестБанк \n";
            String str2 = "    Рег. N ГНИ ККС 123456789\n";
            String str3 = "    КАССОВЫЙ  ЧЕК   N " + userOperation.getId() + "\n";
            String str4 = "    дата " + userOperation.getTimestamp() + "\n";
            String str5 = "    ПРОДАЖА НАЛИЧНОЙ ВАЛЮТЫ  \n";
            String str5_1 = "    ЗА НАЛИЧНЫЕ БЕЛ.РУБЛИ \n";
            String str6 = "    Курс операции           " + userOperation.getRate() + "\n";
            String total = str1 + str2 + str3 + str4 + str5 + str5_1 + str6;
            document.add(new Paragraph(total, font));
            String str_separator = "   +--------------------------------------------------+\n";
            String str7 = "";
            String str8 = "";
            String str9 = "";
            String str10 = "";
            for (UserEntry userEntry : userEntriesList) {
                if (!AttributeName.NС.equals(userEntry.getCurrencyId())) {
                    str7 = "    |К выдаче Клиенту                             |\n";
                    for (Currency currency : currencies) {
                        if (userEntry.getCurrencyId().equals(currency.getId())) {
                            str8 = "    |" + currency.getNameRU() + "  в сумме    " + userEntry.getSum() + "|\n";
                        }
                    }
                } else {
                    str9 = "    |Внесено Клиентом                             |\n";
                    for (Currency currency : currencies) {
                        if (userEntry.getCurrencyId().equals(currency.getId())) {
                            str10 = "    |" + currency.getNameRU() + "  в сумме    " + userEntry.getSum() + "|\n";
                        }
                    }
                }
            }
            String total2 = str_separator + str7 + str_separator + str8 + str_separator + str9 + str_separator + str10 + str_separator;
            document.add(new Paragraph(total2, font));
        } catch (DocumentException | ServiceException e) {
            e.printStackTrace();
        }
    }
}