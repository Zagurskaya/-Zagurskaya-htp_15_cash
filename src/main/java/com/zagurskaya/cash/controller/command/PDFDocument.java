package com.zagurskaya.cash.controller.command;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.zagurskaya.cash.util.DataUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * PDF document
 */
public abstract class PDFDocument {
    /**
     * create operation check
     *
     * @param operationId - operation id
     * @param locale      -  locale
     */
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

    protected abstract void createCheckEn(Long operationId, Document document, Font font);

    protected abstract void createCheckRu(Long operationId, Document document, Font font);


}
