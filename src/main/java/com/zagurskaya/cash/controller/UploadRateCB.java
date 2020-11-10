package com.zagurskaya.cash.controller;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.parser.impl.RateCBParser;
import com.zagurskaya.cash.entity.RateCB;
import com.zagurskaya.cash.model.service.RateCBService;
import com.zagurskaya.cash.model.service.impl.RateCBServiceImpl;
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
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class UploadRateCB extends HttpServlet {
    private RateCBService rateCBService = new RateCBServiceImpl();
    private final static String PAGE_UPLOAD_RATE_CB = "/jsp/controller/load_rate_cb.jsp";

    public UploadRateCB() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<RateCB> rateCBList = new ArrayList<>();
                ServletFileUpload fileUpload = new ServletFileUpload();
                FileItemIterator items = fileUpload.getItemIterator(request);

                while (items.hasNext()) {
                    FileItemStream item = items.next();
//                    String fieldValue = item.getString();
                    if (!item.isFormField()) {
                        InputStream is = item.openStream();
                        String fieldName = item.getName();
                        String value = Streams.asString(is);
                        RateCBParser rateCBParser = new RateCBParser();
                        List<String> rows = rateCBParser.parsTextToRowList(value);
                        rows.stream().forEach(s -> rateCBList.add(rateCBParser.parse(s)));
                    }
                }
                rateCBService.create(rateCBList);
                request.setAttribute(AttributeName.MESSAGE, "File Uploaded Successfully");

            } catch (Exception ex) {
                request.setAttribute(AttributeName.ERROR, "100 File Upload Failed due to " + ex);
            }
        } else {
            request.setAttribute(AttributeName.ERROR, "100 No File found");
        }
        request.getRequestDispatcher(PAGE_UPLOAD_RATE_CB).forward(request, response);

    }


}