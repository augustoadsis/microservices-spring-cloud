package com.microservices.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;

@Component
public class RestTemplateConfig {

    @Bean
    @RequestScope
    public RestTemplate restTemplate(HttpServletRequest request) {
        // retrieve the auth header from incoming request
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final RestTemplate restTemplate = new RestTemplate();
        // add a token if an incoming auth header exists, only
        if (authHeader != null && !authHeader.isEmpty()) {
            // since the header should be added to each outgoing request,
            // add an interceptor that handles this.
            restTemplate.getInterceptors().add((outReq, bytes, clientHttpReqExec) -> {
                outReq.getHeaders().set(HttpHeaders.AUTHORIZATION, authHeader);
                return clientHttpReqExec.execute(outReq, bytes);
            });
        }
        return restTemplate;
    }
}
