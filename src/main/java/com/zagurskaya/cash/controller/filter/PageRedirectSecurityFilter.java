package com.zagurskaya.cash.controller.filter;

import com.zagurskaya.cash.controller.command.ActionType;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter for safe page redirection
 */
public class PageRedirectSecurityFilter implements Filter {
    private static final String DO_COMMAND = "/do?command=";

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath() + DO_COMMAND + ActionType.INDEX.name().toLowerCase());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}