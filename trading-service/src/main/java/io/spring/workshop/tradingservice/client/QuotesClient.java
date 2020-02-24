package io.spring.workshop.tradingservice.client;

import io.spring.workshop.tradingservice.dto.Quote;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Component
public class QuotesClient {

    private final WebClient webClient;

    public QuotesClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Flux<Quote> quotesFeed() {
        return webClient.get().uri("http://localhost:8081/quotes").retrieve().bodyToFlux(Quote.class);
    }

    public Mono<Quote> getLatestQuote(String ticker) {
        return quotesFeed().filter(q -> q.getTicker().equals(ticker)).
                next().
                timeout(Duration.ofSeconds(15))
                .onErrorReturn(TimeoutException.class, new Quote(ticker, BigDecimal.ZERO));
    }
}
