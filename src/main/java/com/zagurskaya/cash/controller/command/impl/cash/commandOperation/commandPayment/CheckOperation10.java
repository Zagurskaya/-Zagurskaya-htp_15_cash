package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.zagurskaya.cash.controller.command.PDFDocument;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.PaymentService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import com.zagurskaya.cash.model.service.impl.PaymentServiceImpl;

import java.io.IOException;
import java.util.List;


public class CheckOperation10 implements PDFDocument {
    private final CurrencyService currencyService = new CurrencyServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();

    public void createCheckEn(Long operationId) {

        try {
            UserOperation userOperation = paymentService.findUserOperationById(operationId);
            List<UserEntry> userEntriesList = paymentService.findAllUserEntriesByOperationId(operationId);
            List<Currency> currencies = currencyService.findAll();
            String file = "d:/Java/Epam/htp_15_cash/check/check10.pdf";

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
            Document doc = new Document(pdfDoc);
            String str1 = "    OAO TestBank \n";
            String str2 = "    Registration Number N KKS 123456789\n";
            String str3 = "    CASHIER'S CHECK N " + userOperation.getId() + "\n";
            String str4 = "    date " + userOperation.getTimestamp() + "\n";
            String str5 = "    Exchange rate           " + userOperation.getRate() + "\n";
            String str6 = "    Buying  foreign cash for cash Bel. rubles \n";
            String total = str1 + str2 + str3 + str4 + str5 + str6;
            doc.add(new Paragraph(new Text(total)));

            String str_separator = "    +--------------------------------------------------+\n";
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
            doc.add(new Paragraph(new Text(total2)));
            doc.close();
        } catch (ServiceException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCheckRu(Long operationId) {

    }
}