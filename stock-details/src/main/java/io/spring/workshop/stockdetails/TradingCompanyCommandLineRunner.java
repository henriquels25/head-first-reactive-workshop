package io.spring.workshop.stockdetails;

import io.spring.workshop.stockdetails.domain.TradingCompany;
import io.spring.workshop.stockdetails.repository.TradingCompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class TradingCompanyCommandLineRunner implements CommandLineRunner {

    private final TradingCompanyRepository tradingCompanyRepository;

    public TradingCompanyCommandLineRunner(TradingCompanyRepository tradingCompanyRepository) {
        this.tradingCompanyRepository = tradingCompanyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        TradingCompany dell = new TradingCompany("Dell Technologies Inc", "DELL");
        TradingCompany ctxs = new TradingCompany("Citrix Systems, Inc.", "CTXS");
        TradingCompany goog = new TradingCompany("Alphabet Inc Class C", "GOOG");
        TradingCompany orcl = new TradingCompany("Oracle Corporation", "ORCL");
        TradingCompany msft = new TradingCompany("Microsoft Corporation", "MSFT");
        TradingCompany ptrb = new TradingCompany("Petroleo Brasileiro SA Petrobras Preference Shares", "PETR4");
        tradingCompanyRepository.saveAll(Flux.just(dell, ctxs, goog, orcl, msft, ptrb)).then().block();
    }
}
