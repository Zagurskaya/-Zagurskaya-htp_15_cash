package com.zagurskaya.cash.controller.check;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.util.DataProperty;
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
public interface PDFDocument {
    String FONT = "font/arial.ttf";
    String CHECK_PATH = DataProperty.checkPatch;

    /**
     * create operation check
     *
     * @param operationId - operation id
     * @param locale      -  locale
     */
    default void createCheck(Long operationId, int operationNumber, String locale) {
        final Logger logger = LogManager.getLogger(PDFDocument.class);

        Document document = new Document();
        try {
            LocalDateTime now = LocalDateTime.now();
            String dateTime = DataUtil.getFormattedLocalDateTime(now);
            File file = new File(CHECK_PATH + dateTime + "check" + operationNumber + ".pdf");
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

    void createCheckEn(Long operationId, Document document, Font font);

    void createCheckRu(Long operationId, Document document, Font font);


}
