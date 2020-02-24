package io.spring.workshop.tradingservice.controller;

import io.spring.workshop.tradingservice.client.TradingCompanyClient;
import io.spring.workshop.tradingservice.dto.TradingCompany;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TradingCompanyController {

    private final TradingCompanyClient tradingCompanyClient;

    public TradingCompanyController(TradingCompanyClient tradingCompanyClient) {
        this.tradingCompanyClient = tradingCompanyClient;
    }

    @GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TradingCompany> getCompanies() {
        return tradingCompanyClient.getAllCompanies();
    }

    @GetMapping(value = "/details/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TradingCompany> getCompanyByTicker(@PathVariable String ticker) {
        return tradingCompanyClient.getCompany(ticker);
    }
}
