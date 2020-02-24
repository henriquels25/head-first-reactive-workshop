package io.spring.workshop.stockquotes.handler;

import io.spring.workshop.stockquotes.domain.Quote;
import io.spring.workshop.stockquotes.service.QuoteGenerator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@Component
public class QuoteHandler {

    private final QuoteGenerator quoteGenerator;

    public QuoteHandler(QuoteGenerator quoteGenerator) {
        this.quoteGenerator = quoteGenerator;
    }

    public Mono<ServerResponse> getQuotes(ServerRequest serverRequest) {
        Flux<Quote> quotes = quoteGenerator.fetchQuoteStream();
        return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(quotes, Quote.class);
    }
}
