package com.gmail.zagurskaya.servlet;

import com.gmail.zagurskaya.reader.DataReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/read")
public class ReadServlet extends HttpServlet {
    private DataReader dataReader;

    @Override
    public void init() throws ServletException {
        this.dataReader = new DataReader();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/jsp/read.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = dataReader.read("/data/tex.txt");
        request.setAttribute("text", text);
        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/jsp/read.jsp");
        requestDispatcher.forward(request, response);
        //        doGet(request, response);
    }
}
