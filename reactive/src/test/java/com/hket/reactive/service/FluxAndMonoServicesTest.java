package com.hket.reactive.service;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

public class FluxAndMonoServicesTest {
    
    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    // ("Orange") : Basic Mono
    @Test
    void testFruitsMono() {
        var fruitsMono = fluxAndMonoServices.fruitsMono();
        StepVerifier.create(fruitsMono)
            .expectNext("Orange")
            .verifyComplete();
    }

    // ("Mango","Orange","Banana") : Basic Flux
    @Test
    void testFruitsFlux() {
        var fruitsFlux = fluxAndMonoServices.fruitsFlux();
        StepVerifier.create(fruitsFlux)
            .expectNext("Mango","Orange","Banana")
            .verifyComplete();
    }

    // ("Mango","Orange","Banana") : Filter (length>5)
    @Test
    void testFruitsFluxFilter() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxFilter(5);
        StepVerifier.create(fruitsFlux)
            .expectNext("Orange","Banana")
            .verifyComplete();
    }

    // ("Mango","Orange","Banana") : Filter (length>5) and toLowerCase
    @Test
    void testFruitsFluxFilterMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxFilterMap(5);
        StepVerifier.create(fruitsFlux)
            .expectNext("orange","banana")
            .verifyComplete();
    }

    // ("Mango","Orange","Banana") : flatmap  split("")
    @Test
    void testFruitsFluxFlatMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxFlatMap();
        StepVerifier.create(fruitsFlux)
            .expectNextCount(17)
            .verifyComplete();
    }

    // ("Mango","Orange","Banana") : Async
    @Test
    void testFruitsFluxFlatMapAsync() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxFlatMapAsync();
        StepVerifier.create(fruitsFlux)
            .expectNextCount(17)
            .verifyComplete();
    }

    // ("Mango","Orange","Banana") : Delay 1s
    @Test
    void testFruitsFluxConcatMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxConcatMap();
        StepVerifier.create(fruitsFlux)
            .expectNextCount(17)
            .verifyComplete();
    }

    // ("Mango","Orange","Banana") : use function to filter length > 5
    @Test
    void testFruitsFluxTranform() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxTranform(5);
        StepVerifier.create(fruitsFlux)
            .expectNext("Orange", "Banana")
            .verifyComplete();
    }

    // If data is empty return default value
    @Test
    void testFruitsFluxTranformDefaultIfEmpty() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxTranformDefaultIfEmpty(10);
        StepVerifier.create(fruitsFlux)
            .expectNext("Default")
            .verifyComplete();
    }

    // If data is empty seitch to other data
    @Test
    void testFruitsFluxTranformSwitchIfEmpty() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxTranformSwitchIfEmpty(9);
        StepVerifier.create(fruitsFlux)
            .expectNext("Jack Fruit")
            .verifyComplete();
    }

    // ("Mango", "Orange") ("Tomato", "Lemon")
    @Test
    void testFruitsFluxConcat() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxConcat();
        StepVerifier.create(fruitsFlux)
            .expectNext("Mango", "Orange", "Tomato", "Lemon")
            .verifyComplete();
    }

    /*
    @Test
    void testFruitsFluxConcatWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxConcatWith();
        StepVerifier.create(fruitsFlux)
            .expectNext("Mango", "Orange", "Tomato", "Lemon")
            .verifyComplete();
    }*/

    // ("Mango", "Orange") ("Tomato", "Lemon")
    @Test
    void testFruitsFluxMerge() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxMerge();
        StepVerifier.create(fruitsFlux)
            .expectNext("Mango", "Tomato", "Orange", "Lemon")
            .verifyComplete();
    }
    /*
    @Test
    void testFruitsFluxMergeWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxMergeWith();
        StepVerifier.create(fruitsFlux)
            .expectNext("Mango", "Tomato", "Orange", "Lemon")
            .verifyComplete();
    }*/

    // ("Mango", "Orange") ("Tomato", "Lemon") with sequential
    @Test
    void testFruitsFluxMergeWithSequential() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxMergeWithSequential();
        StepVerifier.create(fruitsFlux)
            .expectNext("Mango", "Orange", "Tomato", "Lemon")
            .verifyComplete();
    }

    //("Mango", "Orange") ("Tomato", "Lemon")
    @Test
    void testFruitsFluxZip() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxZip();
        StepVerifier.create(fruitsFlux)
            .expectNext("MangoTomato", "OrangeLemon")
            .verifyComplete();
    }
    /*
    @Test
    void testFruitsFluxZipWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxZipWith();
        StepVerifier.create(fruitsFlux)
            .expectNext("MangoTomato", "OrangeLemon")
            .verifyComplete();
    }*/

    // ("Mango", "Orange") ("Tomato", "Lemon") ("Potato", "Beans")
    @Test
    void testFruitsFluxZipTuple() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxZipTuple();
        StepVerifier.create(fruitsFlux)
            .expectNext("MangoTomatoPotato", "OrangeLemonBeans")
            .verifyComplete();
    }

    // Mono List 
    @Test
    void testFruitsMonoFlatMap() {
        var fruitsMono = fluxAndMonoServices.fruitsMonoFlatMap();
        StepVerifier.create(fruitsMono)
            .expectNextCount(1)
            .verifyComplete();
    }

    // split Mono to Flux 
    @Test
    void testFruitsMonoFlatMapMany() {
        var fruitsFlux = fluxAndMonoServices.fruitsMonoFlatMapMany();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    // Mono add into other Mono to Flux ()
    @Test
    void testFruitsMonoConcatWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsMonoConcatWith();
        StepVerifier.create(fruitsFlux)
            .expectNext("Mango", "Tomato")
            .verifyComplete();
    }

    // Mono + Mono into Flux
    @Test
    void testFruitsMonoZip() {
        var fruitsFlux = fluxAndMonoServices.fruitsMonoZip();
        StepVerifier.create(fruitsFlux)
            .expectNext("MangoTomato")
            .verifyComplete();
    }
    /*
    @Test
    void testFruitsMonoZipWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsMonoZipWith();
        StepVerifier.create(fruitsFlux)
            .expectNext("MangoTomato")
            .verifyComplete();
    }*/

    // doOnNext, doOnSubscribe, doOnComplete
    @Test
    void testFruitsFluxFilterDoOn() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxFilterDoOn(5);
        StepVerifier.create(fruitsFlux)
            .expectNext("Orange", "Banana")
            .verifyComplete();
    }

    // onError return Exception
    @Test
    void testFruitsFluxOnError() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxOnError();
        StepVerifier.create(fruitsFlux)
            .expectNext("APPLE")
            .expectError(RuntimeException.class)
            .verify();

    }

    // onErrorReturn("Orange")
    @Test
    void testFruitsFluxOnErrorReturn() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxOnErrorReturn();
        StepVerifier.create(fruitsFlux)
            .expectNext("Apple", "Mango", "Orange")
            .verifyComplete();

    }

    // ("Apple", "Mango", "Orange") : "Mango" throw Exception
    @Test
    void testFruitsFluxOnErrorContinue() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxOnErrorContinue();
        StepVerifier.create(fruitsFlux)
            .expectNext("APPLE", "ORANGE")
            .verifyComplete();

    }

    // RuntimeException to IllegalStateException : Custom Exception 
    @Test
    void testFruitsFluxOnErrorMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxOnErrorMap();
        StepVerifier.create(fruitsFlux)
            .expectNext("APPLE")
            .expectError(IllegalStateException.class)
            .verify();
    }

    
}
