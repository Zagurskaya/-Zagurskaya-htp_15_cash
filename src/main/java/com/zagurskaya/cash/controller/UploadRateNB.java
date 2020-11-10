package com.zagurskaya.cash.controller;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.RateNB;
import com.zagurskaya.cash.model.service.RateNBService;
import com.zagurskaya.cash.model.service.impl.RateNBServiceImpl;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class UploadRateNB extends HttpServlet {

    private RateNBService rateNBService = new RateNBServiceImpl();

    public UploadRateNB() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<RateNB> rateNBList = new ArrayList<>();
                ServletFileUpload fileUpload = new ServletFileUpload();
                FileItemIterator items = fileUpload.getItemIterator(request);
                while (items.hasNext()) {
                    FileItemStream item = items.next();
//                    String fieldValue = item.getString();
                    if (!item.isFormField()) {
                        InputStream is = item.openStream();
                        String fieldName = item.getName();
                        String value = Streams.asString(is);
                        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                        List<String> rows = Arrays.stream(value.split("\r\n")).collect(Collectors.toList());
                        rows.stream().forEach(s -> {
                                    List<String> elements = Arrays.stream(s.split(";")).collect(Collectors.toList());
                                    System.out.println(elements);
                                    RateNB rateNB = new RateNB.Builder()
                                            .addCurrencyId(Long.parseLong(elements.get(1)))
//                                                .addDate(context.get(0))
                                            .addDate(date)
                                            .addSum(Double.parseDouble(elements.get(2)))
                                            .build();
                                    rateNBList.add(rateNB);
                                }
                        );
                    }
                }
                rateNBService.create(rateNBList);
                request.setAttribute(AttributeName.MESSAGE, "File Uploaded Successfully");

            } catch (Exception ex) {
                request.setAttribute(AttributeName.ERROR, "100 File Upload Failed due to " + ex);
            }
        } else {
            request.setAttribute(AttributeName.ERROR, "100 No File found");
        }
        request.getRequestDispatcher("/jsp/controller/load_rate_nb.jsp").forward(request, response);

    }


}