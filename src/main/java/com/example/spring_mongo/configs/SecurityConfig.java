package com.example.spring_mongo.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Description(value = "Ignore those end point before jwt filter because those are public end point.")
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/", "/users/**", "/static/**","/roles/**","/blogs/**","/jobs/**","/leave/**")
                // For Swagger
                .antMatchers("/swagger-*/**", "/webjars/**", "/v2/api-docs", "/csrf", "/error");
    }
}
