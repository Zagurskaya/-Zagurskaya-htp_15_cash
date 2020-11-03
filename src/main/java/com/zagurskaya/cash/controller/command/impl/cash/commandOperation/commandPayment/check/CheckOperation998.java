package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment.check;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.zagurskaya.cash.controller.command.PDFDocument;
import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.PaymentService;
import com.zagurskaya.cash.model.service.impl.PaymentServiceImpl;
import com.zagurskaya.cash.util.DataUtil;

import java.util.List;

public class CheckOperation998 extends PDFDocument {
    private final PaymentService paymentService = new PaymentServiceImpl();

    public void createCheckEn(Long operationId, Document document, Font font) {
        try {
            UserOperation userOperation = paymentService.findUserOperationById(operationId);
            List<UserEntry> userEntriesList = paymentService.findAllUserEntriesByOperationId(operationId);
            String str1 = "----------------------------|--------------------------------------------------------------------------\n";
            String str2 = "                                   |  OAO TestBank\n";
            String str3 = "                                   |  ------------------------------------------------------------------------\n";
            String str4 = "                                   |               (the payee)\n";
            String str5 = "OAO TestBank          |  c.BREST ООО \"Roga и Kopita\"\n";
            String str6 = "                                   |   ------------------------------------------------------------------------\n";
            String str7 = "                                   |          (Name of the bank)            Code    TESTBY2X\n";
            String str8 = "                                   |  Beneficiary account " + userOperation.getCheckingAccount() + "\n";
            String str9 = "                                   |                            ----------------------------------------------------\n";
            String str10 = "Zav. N    123456789 |\n";
            String str11 = "B/account                  | " + userOperation.getCheckingAccount() + "\n";
            String str12 = "                                   |  UNP:123456789                  Operation number " + userOperation.getId() + "\n";
            String str13 = DataUtil.getFormattedCheck(userOperation.getTimestamp()) + " |            --------------                                                --------\n";
            String str14 = "Cash voucher N " + userOperation.getId() + " |  " + userOperation.getFullName() + "\n";
            String str15 = "                                   |   ------------------------------------------------------------------------\n";
            String str16 = "                                   |       (surname, first name, patronymic (if any)\n";
            String str17 = "                                   |      " + userOperation.getSpecification() + "\n";
            String str18 = "                                   |   ------------------------------------------------------------------------\n";
            String str19 = "                                   |                       (description)\n";
            String str20 = "                                   |  -------------------------------------------------------------------------\n";
            String str21 = "                                   |  |  Payment type            | Date and time           |   Sum   |\n";
            String str22 = "                                   |  -------------------------------------------------------------------------\n";
            String str23 = "                                   |  |Communal payment     |" + DataUtil.getFormattedCheck(userOperation.getTimestamp()) + "| " + userEntriesList.get(0).getSum() + "  |\n";
            String str24 = "                                   |  |                                     |----------------------------------------\n";
            String str25 = "                                   |  |                                     |   Fine                       |              |\n";
            String str26 = "                                   |   -------------------------------                                  ------------\n";
            String str27 = "                                   |  Without VAT                                          Total | " + userEntriesList.get(0).getSum() + "  |\n";
            String str28 = "RECEIPT                  |                                                                          ------------\n";
            String str29 = "Cashier__________ |  Payer ___________\n";
            String str30 = "----------------------------|----------------------------------------------------------------------------\n";

            String total = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8 + str9 + str10 + str11 + str12 + str13 + str14 + str15
                    + str16 + str17 + str18 + str19 + str20 + str21 + str22 + str23 + str24 + str25 + str26 + str27 + str28 + str29 + str30;
            document.add(new Paragraph(total, font));
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
            String str6 = "                                   |   ------------------------------------------------------------------------\n";
            String str7 = "                                   |          (наименование банка)            Код    TESTBY2X\n";
            String str8 = "                                   |  Счет получателя " + userOperation.getCheckingAccount() + "\n";
            String str9 = "                                   |                            ----------------------------------------------------\n";
            String str10 = "Зав. N    123456789 |\n";
            String str11 = "Р/счет                       | " + userOperation.getCheckingAccount() + "\n";
            String str12 = "                                   |  УНП:123456789                  Номер операции " + userOperation.getId() + "\n";
            String str13 = DataUtil.getFormattedCheck(userOperation.getTimestamp()) + " |            --------------                                                --------\n";
            String str14 = "Кассовый чек N " + userOperation.getId() + " |  " + userOperation.getFullName() + "\n";
            String str15 = "                                   |   ------------------------------------------------------------------------\n";
            String str16 = "                                   |      (фамилия, собственное имя, отчество (при наличии)\n";
            String str17 = "                                   |      " + userOperation.getSpecification() + "\n";
            String str18 = "                                   |   ------------------------------------------------------------------------\n";
            String str19 = "                                   |                       (описание)\n";
            String str20 = "                                   |  -------------------------------------------------------------------------\n";
            String str21 = "                                   |  |  Вид  платежа             | Дата и время         | Сумма |\n";
            String str22 = "                                   |  -------------------------------------------------------------------------\n";
            String str23 = "                                   |  |Коммунальные услуги|" + DataUtil.getFormattedCheck(userOperation.getTimestamp()) + "|" + userEntriesList.get(0).getSum() + "  |\n";
            String str24 = "                                   |  |                                     |----------------------------------------\n";
            String str25 = "                                   |  |                                     |   Пеня                       |             |\n";
            String str26 = "                                   |   -------------------------------                                    -----------\n";
            String str27 = "                                   |  Без НДС                                                 Всего|" + userEntriesList.get(0).getSum() + "  |\n";
            String str28 = "КВИТАНЦИЯ            |                                                                            ------------\n";
            String str29 = "Кассир__________  |  Плательщик ___________\n";
            String str30 = "----------------------------|----------------------------------------------------------------------------\n";

            String total = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8 + str9 + str10 + str11 + str12 + str13 + str14 + str15
                    + str16 + str17 + str18 + str19 + str20 + str21 + str22 + str23 + str24 + str25 + str26 + str27 + str28 + str29 + str30;
            document.add(new Paragraph(total, font));
        } catch (DocumentException | ServiceException e) {
            e.printStackTrace();
        }
    }
}