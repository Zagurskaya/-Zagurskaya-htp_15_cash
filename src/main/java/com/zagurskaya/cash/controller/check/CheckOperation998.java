package com.zagurskaya.cash.controller.check;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.PaymentService;
import com.zagurskaya.cash.model.service.impl.PaymentServiceImpl;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CheckOperation998 implements PDFDocument {
    private static final Logger logger = LogManager.getLogger(CheckOperation998.class);
    private final PaymentService paymentService = new PaymentServiceImpl();

    @Override
    public void createCheckEn(Long operationId, Document document, Font font) {
        try {
            UserOperation userOperation = paymentService.findUserOperationById(operationId);
            List<UserEntry> userEntriesList = paymentService.findAllUserEntriesByOperationId(operationId);
            String check = "----------------------------|--------------------------------------------------------------------------\n" +
                    "                                   |  OAO TestBank\n" +
                    "                                   |  ------------------------------------------------------------------------\n" +
                    "                                   |               (the payee)\n" +
                    "OAO TestBank          |  c.BREST ООО \"Roga и Kopita\"\n" +
                    "                                   |   ------------------------------------------------------------------------\n" +
                    "                                   |          (Name of the bank)            Code    TESTBY2X\n" +
                    "                                   |  Beneficiary account " + userOperation.getCheckingAccount() + "\n" +
                    "                                   |                            ----------------------------------------------------\n" +
                    "Zav. N    123456789 |\n" +
                    "B/account                  | " + userOperation.getCheckingAccount() + "\n" +
                    "                                   |  UNP:123456789                  Operation number " + userOperation.getId() + "\n" +
                    DataUtil.getFormattedCheck(userOperation.getLocalDateTime()) + " |            --------------                                                --------\n" +
                    "Cash voucher N " + userOperation.getId() + " |  " + userOperation.getFullName() + "\n" +
                    "                                   |   ------------------------------------------------------------------------\n" +
                    "                                   |       (surname, first name, patronymic (if any)\n" +
                    "                                   |      " + userOperation.getSpecification() + "\n" +
                    "                                   |   ------------------------------------------------------------------------\n" +
                    "                                   |                       (description)\n" +
                    "                                   |  -------------------------------------------------------------------------\n" +
                    "                                   |  |  Payment type            | Date and time           |   Sum   |\n" +
                    "                                   |  -------------------------------------------------------------------------\n" +
                    "                                   |  |Communal payment     |" + DataUtil.getFormattedCheck(userOperation.getLocalDateTime()) + "| " + userEntriesList.get(0).getSum() + "  |\n" +
                    "                                   |  |                                     |----------------------------------------\n" +
                    "                                   |  |                                     |   Fine                       |              |\n" +
                    "                                   |   -------------------------------                                  ------------\n" +
                    "                                   |  Without VAT                                          Total | " + userEntriesList.get(0).getSum() + "  |\n" +
                    "RECEIPT                  |                                                                          ------------\n" +
                    "Cashier__________ |  Payer ___________\n" +
                    "----------------------------|----------------------------------------------------------------------------\n";

            document.add(new Paragraph(check, font));
        } catch (DocumentException | ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

    @Override
    public void createCheckRu(Long operationId, Document document, Font font) {
        try {
            UserOperation userOperation = paymentService.findUserOperationById(operationId);
            List<UserEntry> userEntriesList = paymentService.findAllUserEntriesByOperationId(operationId);
            String check = "----------------------------|--------------------------------------------------------------------------\n" +
                    "                                   |  ЗАО \"ТестБанк\"\n" +
                    "                                   |  ------------------------------------------------------------------------\n" +
                    "                                   |               (получатель платежа)\n" +
                    "ЗАО \"ТестБанк\"        |  Г.БРЕСТ ООО \"Рога и копыта\"\n" +
                    "                                   |   ------------------------------------------------------------------------\n" +
                    "                                   |          (наименование банка)            Код    TESTBY2X\n" +
                    "                                   |  Счет получателя " + userOperation.getCheckingAccount() + "\n" +
                    "                                   |                            ----------------------------------------------------\n" +
                    "Зав. N    123456789 |\n" +
                    "Р/счет                       | " + userOperation.getCheckingAccount() + "\n" +
                    "                                   |  УНП:123456789                  Номер опрации " + userOperation.getId() + "\n" +
                    DataUtil.getFormattedCheck(userOperation.getLocalDateTime()) + " |            --------------                                                --------\n" +
                    "Кассовый чек N " + userOperation.getId() + " |  " + userOperation.getFullName() + "\n" +
                    "                                   |   ------------------------------------------------------------------------\n" +
                    "                                   |      (фамилия, собственное имя, отчество (при наличии)\n" +
                    "                                   |      " + userOperation.getSpecification() + "\n" +
                    "                                   |   ------------------------------------------------------------------------\n" +
                    "                                   |                       (описание)\n" +
                    "                                   |  -------------------------------------------------------------------------\n" +
                    "                                   |  |  Вид  платежа             | Дата и время         | Сумма |\n" +
                    "                                   |  -------------------------------------------------------------------------\n" +
                    "                                   |  |Коммунальные услуги|" + DataUtil.getFormattedCheck(userOperation.getLocalDateTime()) + "|" + userEntriesList.get(0).getSum() + "  |\n" +
                    "                                   |  |                                     |----------------------------------------\n" +
                    "                                   |  |                                     |   Пеня                       |             |\n" +
                    "                                   |   -------------------------------                                    -----------\n" +
                    "                                   |  Без НДС                                                 Всего|" + userEntriesList.get(0).getSum() + "  |\n" +
                    "КВИТАНЦИЯ            |                                                                            ------------\n" +
                    "Кассир__________  |  Плательщик ___________\n" +
                    "----------------------------|----------------------------------------------------------------------------\n";


            document.add(new Paragraph(check, font));
        } catch (DocumentException | ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }
}