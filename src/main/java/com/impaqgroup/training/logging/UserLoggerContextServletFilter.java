package com.impaqgroup.training.logging;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class UserLoggerContextServletFilter implements Filter {

    public static final String MDC_KEY_USER_NAME = "user_name";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userName = getUserName((HttpServletRequest) servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private String getUserName(HttpServletRequest servletRequest) {
        return servletRequest.getRemoteUser();
    }
}
