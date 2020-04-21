package com.microservices.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class WebClientConfig {

    @Bean
    @RequestScope
    public WebClient webClient(HttpServletRequest request) {
        // retrieve the auth header from incoming request
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // add a token if an incoming auth header exists, only
        if (authHeader != null && !authHeader.isEmpty()) {
            // since the header should be added to each outgoing request,
            // add an interceptor that handles this.

            WebClient client = WebClient.builder().filter((req, next) -> {
                ClientRequest filtered = ClientRequest.from(req)
                        .header(HttpHeaders.AUTHORIZATION, authHeader)
                        .build();
                return next.exchange(filtered);
            }).build();
            return client;
        }
        return  null;
    }

}
