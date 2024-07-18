package com.AppRH.AppRH.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
public class WebConfig extends WebSecurityConfiguration {

    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {

        builder
        .inMemoryAuthentication()
        .withUser("Gustavo").password("{noop}Gustavo").roles("USER")
        .and().withUser("root").password("{noop}root").roles("ADMIN");
    }
}
