package com.hket.reactivewebservice.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingRouterTest {
    
    // Spring Boot will create a 'WebTestClient' for you,
    // already configure and ready to issus requests against "localhost:RANDOM_PORT"
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testHello() {
        webTestClient
            // Create a GET request to test an endpoint
            .get().uri("/hello")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            // and use the dedicated DSL to test assertions against the response
            .expectStatus().isOk()
            .expectBody(Greeting.class).value(greeting -> {
                assertThat(greeting.getMessage()).isEqualTo("Hello, Spring!");
            });
    }

    @Test
    public void mono() {
        String expectedValue = "Spring";
        webTestClient.get().uri("/mono")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .consumeWith((response) -> {
                assertEquals(expectedValue, response.getResponseBody());
            });
    }
    
    @Test
    public void flux_approach1() {
        Flux<Integer> integerFlux = webTestClient.get()
            .uri("/flux")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .returnResult(Integer.class)
            .getResponseBody();
            
        StepVerifier.create(integerFlux)
            .expectSubscription()
            .expectNext(1)
            .expectNext(2)
            .expectNext(3)
            .expectNext(4)
            .verifyComplete();
    }
}
