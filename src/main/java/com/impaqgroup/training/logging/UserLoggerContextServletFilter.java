package com.impaqgroup.training.logging;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;

public class UserLoggerContextServletFilter implements Filter{

    public static final String MDC_KEY_USER_NAME = "user_name";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userName = getUserName((HttpServletRequest) servletRequest);
        MDC.put(MDC_KEY_USER_NAME, userName);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            MDC.remove(MDC_KEY_USER_NAME);
        }
    }

    @Override
    public void destroy() {

    }

    private String getUserName(HttpServletRequest servletRequest) {
        return servletRequest.getRemoteUser();
    }
}
