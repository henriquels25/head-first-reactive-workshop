package io.spring.workshop.stockdetails.controller;

import io.spring.workshop.stockdetails.domain.TradingCompany;
import io.spring.workshop.stockdetails.repository.TradingCompanyRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TradingCompanyController {

    private final TradingCompanyRepository tradingCompanyRepository;

    public TradingCompanyController(TradingCompanyRepository tradingCompanyRepository) {
        this.tradingCompanyRepository = tradingCompanyRepository;
    }

    @GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TradingCompany> getCompanies() {
        return tradingCompanyRepository.findAll();
    }

    @GetMapping(value = "/details/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TradingCompany> getCompanyByTicker(@PathVariable String ticker) {
        return tradingCompanyRepository.findByTicker(ticker);
    }
}
