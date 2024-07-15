package com.zzootec.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = TokenHolder.getToken();
        if (token != null) {
            requestTemplate.header(AUTHORIZATION_HEADER, token);
        }
    }
}
