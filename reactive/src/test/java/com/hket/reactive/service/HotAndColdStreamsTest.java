package com.hket.reactive.service;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;


public class HotAndColdStreamsTest {
    
    @Test
    public void coldStreamTest() {
        var numbers = Flux.range(1,10);
        numbers.subscribe(integer -> System.out.println("Subscriber 1 = " + integer));
        numbers.subscribe(integer -> System.out.println("Subscriber 2 = " + integer));
    }

    @SneakyThrows
    @Test
    public void hotStreamTest() {
        var numbers = Flux.range(1,10)
            .delayElements(Duration.ofMillis(1000));

        ConnectableFlux<Integer> publisher = numbers.publish();
        publisher.connect();

        publisher.subscribe(i -> System.out.println("Subscriber 1 = " + i));
        Thread.sleep(5000);
        publisher.subscribe(i -> System.out.println("Subscriber 2 = " + i));
        Thread.sleep(10000);
    }
}
