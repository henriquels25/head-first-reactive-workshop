package io.spring.workshop.stockquotes;

import io.spring.workshop.stockquotes.domain.Quote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
class StockQuotesE2ETest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getTestsClient() {
        List<Quote> quotes = webTestClient.get().uri("/quotes").exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_STREAM_JSON)
                .returnResult(Quote.class)
                .getResponseBody()
                .take(20)
                .collectList()
                .block();

        assertThat(quotes).allSatisfy(q -> assertThat(q.getPrice()).isPositive());

    }

}
