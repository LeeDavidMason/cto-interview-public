package com.citi.cto.ctointerviewpublic;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class HttpClient {

    private final WebClient webClient;

    public HttpClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public <T> T get(String url, Class<T> responseType) throws WebClientResponseException {
        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        ClientResponse::createException)
                .bodyToMono(responseType)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)))
                .block();
    }
}
