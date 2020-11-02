package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.zagurskaya.cash.controller.command.PDFDocument;
import com.zagurskaya.cash.controller.util.RegexPattern;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.PaymentService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import com.zagurskaya.cash.model.service.impl.PaymentServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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


//            Text text1 = new Text("Tutorials Point originated from the idea that there exists.");
//            Text text2 = new Text("The journey commenced with a single tutorial on HTML in 2006");
//
//            Paragraph para1 = new Paragraph(text1);
//            Paragraph para2 = new Paragraph(text2);
//
//            doc.add(para1);
//            doc.add(para2);

            doc.add(new Paragraph(new Text("    OAO TestBank ")));
            doc.add(new Paragraph(new Text("    Registration Number N KKS 123456789")));
            doc.add(new Paragraph(new Text("    CASHIER'S CHECK N " + userOperation.getId())));
            doc.add(new Paragraph(new Text("    date " + userOperation.getTimestamp())));
            doc.add(new Paragraph(new Text("    Exchange rate           " + userOperation.getRate())));
            for (UserEntry userEntry : userEntriesList) {
                if (userEntry.getCurrencyId() != 933) {
                    doc.add(new Paragraph(new Text("    +--------------------------------------------------+")));
                    doc.add(new Paragraph(new Text("    |Submitted by the Client                           |")));
                    doc.add(new Paragraph(new Text("    +--------------------------------------------------+")));
                    for (Currency currency : currencies) {
                        if (userEntry.getCurrencyId().equals(currency.getId())) {
                            doc.add(new Paragraph(new Text("    |" + currency.getNameEN() + " in total" + userEntry.getSum() + "        |")));
                        }
                    }
                } else {
                    doc.add(new Paragraph(new Text("    +--------------------------------------------------+")));
                    doc.add(new Paragraph(new Text("    |To be issued to the Client                        |")));
                    doc.add(new Paragraph(new Text("    +--------------------------------------------------+")));
                    for (Currency currency : currencies) {
                        if (userEntry.getCurrencyId().equals(currency.getId())) {
                            doc.add(new Paragraph(new Text("    |" + currency.getNameEN() + " in total" + userEntry.getSum() + "        |")));
                        }
                    }
                    doc.add(new Paragraph(new Text("    +--------------------------------------------------+")));
                }
            }
            doc.close();
        } catch (ServiceException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCheckRu(Long operationId) {

    }
}