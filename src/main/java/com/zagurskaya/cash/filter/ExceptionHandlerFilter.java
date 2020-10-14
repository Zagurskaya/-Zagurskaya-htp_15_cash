package com.zagurskaya.cash.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Обработчик исключений
 */
public class ExceptionHandlerFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(ExceptionHandlerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            ((HttpServletResponse) response).sendError(500, "Internal server error");
            logger.log(Level.ERROR, "Internal server error", e);
        }
    }

    @Override
    public void destroy() {

    }
}
