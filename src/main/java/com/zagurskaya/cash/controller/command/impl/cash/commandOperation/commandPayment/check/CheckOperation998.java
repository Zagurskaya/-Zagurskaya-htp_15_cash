package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment.check;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.PDFDocument;
import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.PaymentService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import com.zagurskaya.cash.model.service.impl.PaymentServiceImpl;
import com.zagurskaya.cash.util.DataUtil;

import java.util.List;

public class CheckOperation998 extends PDFDocument {
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
            String str1 = "----------------------------|--------------------------------------------------------------------------\n";
            String str2 = "                                   |  ЗАО \"ТестБанк\"\n";
            String str3 = "                                   |  ------------------------------------------------------------------------\n";
            String str4 = "                                   |               (получатель платежа)\n";
            String str5 = "ЗАО \"ТестБанк\"        |  Г.БРЕСТ ООО \"Рога и копыта\"\n";
            String str6 = "                                   |          (наименование банка)            Код    TESTBY2X\n";
            String str7 = "                                   |  Счет получателя " + userOperation.getCheckingAccount() + "\n";
            String str8 = "                                   |                            ----------------------------------------------------\n";
            String str9 = "Зав. N    123456789 |\n";
            String str10 = "Р/счет                       | " + userOperation.getCheckingAccount() + "\n";
            String str11 = "                                   |  УНП:123456789                  Номер операции " + userOperation.getId() + "\n";
            String str12 = DataUtil.getFormattedCheck(userOperation.getTimestamp()) + " |            --------------                                                --------\n";
            String str13 = "Кассовый чек N " + userOperation.getId() + " |  " + userOperation.getFullName() + "\n";
            String str14 = "                                   |(фамилия, собственное имя, отчество (при наличии)\n";
            String str15 = "                                   |\n";
            String str16 = "                                   |      " + userOperation.getSpecification() + "\n";
            String str17 = "                                   |   ------------------------------------------------------------------------\n";
            String str18 = "                                   |                       (описание)\n";
            String str19 = "                                   |  -------------------------------------------------------------------------\n";
            String str20 = "                                   |  |  Вид  платежа             | Дата и время         | Сумма |\n";
            String str21 = "                                   |  -------------------------------------------------------------------------\n";
            String str22 = "                                   |  |Коммунальные услуги|" +  DataUtil.getFormattedCheck(userOperation.getTimestamp())+"|" + userEntriesList.get(0).getSum() + "  |\n";
            String str23 = "                                   |  |                                     |----------------------------------------\n";
            String str24 = "                                   |  |                                     |   Пеня                       |             |\n";
            String str25 = "                                   |   -------------------------------                                    -----------\n";
            String str26 = "                                   |  Без НДС                                                 Всего|" + userEntriesList.get(0).getSum() + "  |\n";
            String str27 = "КВИТАНЦИЯ            |                                                                            ------------\n";
            String str28 = "Кассир__________  |  Плательщик ___________\n";
            String str29 = "----------------------------|----------------------------------------------------------------------------\n";

            String total = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8 + str9 + str10 + str11 + str12 + str13 + str14 + str15
                    + str16 + str17 + str18 + str19 + str20 + str21 + str22 + str23 + str24 + str25 + str26 + str27 + str28 + str29;
            document.add(new Paragraph(total, font));
        } catch (DocumentException | ServiceException e) {
            e.printStackTrace();
        }
    }
}