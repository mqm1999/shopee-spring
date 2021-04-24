package com.example.codese_spring.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<CustomFilter> loggingConfig() {
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomFilter());
        registrationBean.addUrlPatterns("/account/info", "/product/order");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CustomFilterId> loggingConfigFilterId() {
            FilterRegistrationBean<CustomFilterId> registrationBean = new FilterRegistrationBean<>();
            registrationBean.setFilter(new CustomFilterId());
            registrationBean.addUrlPatterns("/account/info", "/product/order");
            return registrationBean;

    }

    @Bean
    public FilterRegistrationBean<CustomFilterAdmin> loggingConfigFilterAdmin() {
        FilterRegistrationBean<CustomFilterAdmin> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomFilterAdmin());
        registrationBean.addUrlPatterns("/account/info", "/product/order");
        return registrationBean;

    }
}


