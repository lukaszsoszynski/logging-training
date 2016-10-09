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

    private final static int FILTER_ORDER_COMMONS_REQUEST_LOGGING_FILTER = 1;
    public static final String ALL_REQUEST_PATH = "/*";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and().withUser("ala").password("makota").roles("USER")
                .and().withUser("bartek").password("mapsa").roles("USER")
                .and().withUser("admin").password("password").roles("ADMIN");
    }

    @Bean
    public SecurityConfigurationAdapter securityConfigurationAdapter() {
        return new SecurityConfigurationAdapter();
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        CommonsRequestLoggingFilter filter = createLoggingFilter();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setOrder(FILTER_ORDER_COMMONS_REQUEST_LOGGING_FILTER);
        filterRegistrationBean.setMatchAfter(false);
        filterRegistrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR));
        filterRegistrationBean.setUrlPatterns(Collections.singleton(ALL_REQUEST_PATH));
        return filter;
    }

    private CommonsRequestLoggingFilter createLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        return filter;
    }

}
