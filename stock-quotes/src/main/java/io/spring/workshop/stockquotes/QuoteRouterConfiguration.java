package io.spring.workshop.stockquotes;

import io.spring.workshop.stockquotes.handler.QuoteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuoteRouterConfiguration {

    @Bean
    public RouterFunction routerFunction(QuoteHandler quoteHandler) {
        return route().GET("/quotes", quoteHandler::getQuotes).build();
    }
}
