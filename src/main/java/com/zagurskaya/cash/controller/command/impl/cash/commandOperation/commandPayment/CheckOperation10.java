package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.PDFDocument;
import com.zagurskaya.cash.controller.command.PathPage;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.PaymentService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import com.zagurskaya.cash.model.service.impl.PaymentServiceImpl;
import com.zagurskaya.cash.util.DataUtil;

public class CheckOperation10 implements PDFDocument {
    private final CurrencyService currencyService = new CurrencyServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();

    public void createCheck(Long operationId, int operationNumber, String locale) {
        Document document = new Document();
        try {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            String dateTime = DataUtil.getFormattedTimestamp(now);
            File file = new File(PathPage.PATH_CHECK + dateTime + "check" + operationNumber + ".pdf");
            if (!file.exists()) {
                file.createNewFile();
            }
            BaseFont baseFont = BaseFont.createFont("c:\\Windows\\Fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Font font = new Font(baseFont);
            if (locale.equals(AttributeName.LOCALE_EN)) {
                createCheckEn(operationId, document, font);
            } else {
                createCheckRu(operationId, document, font);
            }

            document.close();
            writer.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createCheckEn(Long operationId, Document document, Font font) {
        try {
            UserOperation userOperation = paymentService.findUserOperationById(operationId);
            List<UserEntry> userEntriesList = paymentService.findAllUserEntriesByOperationId(operationId);
            List<Currency> currencies = currencyService.findAll();

            String str1 = "    OAO TestBank \n";
            String str2 = "    Registration Number N KKS 123456789\n";
            String str3 = "    CASHIER'S CHECK N " + userOperation.getId() + "\n";
            String str4 = "    date " + userOperation.getTimestamp() + "\n";
            String str5 = "    Exchange rate           " + userOperation.getRate() + "\n";
            String str6 = "    Buying  foreign cash for cash Bel. rubles \n";
            String total = str1 + str2 + str3 + str4 + str5 + str6;
            document.add(new Paragraph(total, font));
            String str_separator = "   +--------------------------------------------------+\n";
            String str7 = "";
            String str8 = "";
            String str9 = "";
            String str10 = "";
            for (UserEntry userEntry : userEntriesList) {
                if (userEntry.getCurrencyId() != 933) {
                    str7 = "    |Submitted by the Client                         |\n";
                    for (Currency currency : currencies) {
                        if (userEntry.getCurrencyId().equals(currency.getId())) {
                            str8 = "    |" + currency.getNameEN() + "    in total    " + userEntry.getSum() + "        |\n";
                        }
                    }
                } else {
                    str9 = "    |To be issued to the Client                     |\n";
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

    private void createCheckRu(Long operationId, Document document, Font font) {
        try {
            UserOperation userOperation = paymentService.findUserOperationById(operationId);
            List<UserEntry> userEntriesList = paymentService.findAllUserEntriesByOperationId(operationId);
            List<Currency> currencies = currencyService.findAll();
            String str1 = "    ОАО ТестБанк \n";
            String str2 = "    Рег. N ГНИ ККС 123456789\n";
            String str3 = "    КАССОВЫЙ  ЧЕК   N " + userOperation.getId() + "\n";
            String str4 = "    дата " + userOperation.getTimestamp() + "\n";
            String str5 = "    ПОКУПКА НАЛИЧНОЙ ВАЛЮТЫ \n";
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
                if (userEntry.getCurrencyId() != 933) {
                    str7 = "    |Внесено Клиентом                             |\n";
                    for (Currency currency : currencies) {
                        if (userEntry.getCurrencyId().equals(currency.getId())) {
                            str8 = "    |" + currency.getNameRU() + "  в сумме    " + userEntry.getSum() + "|\n";
                        }
                    }
                } else {
                    str9 = "    |К выдаче Клиенту                             |\n";
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