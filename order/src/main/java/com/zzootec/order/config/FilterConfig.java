package com.zzootec.order.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TokenFilter> loggingFilter() {
        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenFilter());
        registrationBean.addUrlPatterns("/api/v1/orders/*");
        return registrationBean;
    }

}
