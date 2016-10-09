package com.impaqgroup.training.logging;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected final void configure(HttpSecurity http) throws Exception {
        /*@formatter:off*/
        http
            .authorizeRequests()
                .anyRequest().fullyAuthenticated()
        .and()
            .httpBasic()
        .and()
            .csrf().disable();
        /*@formatter:on*/
    }
}
