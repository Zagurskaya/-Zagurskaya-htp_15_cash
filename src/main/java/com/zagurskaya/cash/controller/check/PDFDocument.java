package com.zagurskaya.cash.controller.check;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.PathPage;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * PDF document
 */
public abstract class PDFDocument {
    private static final Logger logger = LogManager.getLogger(PDFDocument.class);
    private static final String FONT = "font/arial.ttf";

    /**
     * create operation check
     *
     * @param operationId - operation id
     * @param locale      -  locale
     */
    public void createCheck(Long operationId, int operationNumber, String locale) {
        Document document = new Document();
        try {
            LocalDateTime now = LocalDateTime.now();
            String dateTime = DataUtil.getFormattedLocalDateTime(now);
            File file = new File(PathPage.PATH_CHECK + dateTime + "check" + operationNumber + ".pdf");
            if (!file.exists()) {
                file.createNewFile();
            }
            BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
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
            logger.log(Level.ERROR, e);
        }
    }

    protected abstract void createCheckEn(Long operationId, Document document, Font font);

    protected abstract void createCheckRu(Long operationId, Document document, Font font);


}
