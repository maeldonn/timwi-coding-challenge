package com.spotifychallenge.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${spotify-api.token}")
    private String token;

    @Value("${spotify-api.base-url}")
    private String baseUrl;

    @Bean
    public WebClient configure() {
        return WebClient.builder()
                .defaultHeaders(h -> h.setBearerAuth(token))
                .baseUrl(baseUrl)
                .build();
    }
}
