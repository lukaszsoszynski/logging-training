package com.impaqgroup.training.logging;

import java.util.Collections;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class Application {

    private static final int FILTER_ORDER_USER_LOGGER_CONTEXT_FILTER = 1;
    private final static int FILTER_ORDER_COMMONS_REQUEST_LOGGING_FILTER = FILTER_ORDER_USER_LOGGER_CONTEXT_FILTER + 1;

    public static final String ALL_REQUEST_PATH = "/*";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*@formatter:off*/
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and().withUser("ala").password("makota").roles("USER")
                .and().withUser("bartek").password("mapsa").roles("USER")
                .and().withUser("admin").password("password").roles("ADMIN");
        /*@formatter:on*/
    }

    @Bean
    public SecurityConfigurationAdapter securityConfigurationAdapter() {
        return new SecurityConfigurationAdapter();
    }

    @Bean
    public FilterRegistrationBean requestLoggingFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(createLoggingFilter());
        filterRegistrationBean.setOrder(FILTER_ORDER_COMMONS_REQUEST_LOGGING_FILTER);
        filterRegistrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR));
        filterRegistrationBean.setUrlPatterns(Collections.singleton(ALL_REQUEST_PATH));
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean userLoggerContextFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new UserLoggerContextServletFilter());
        filterRegistrationBean.setOrder(FILTER_ORDER_USER_LOGGER_CONTEXT_FILTER);
        filterRegistrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR));
        filterRegistrationBean.setUrlPatterns(Collections.singleton(ALL_REQUEST_PATH));
        return filterRegistrationBean;
    }

    private CommonsRequestLoggingFilter createLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        return filter;
    }

}
