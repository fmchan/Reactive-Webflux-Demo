package com.hket.reactive.service;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureTest {
    
    @Test
    public void testBackPresssure() {
        var numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("integer = " + integer));
        numbers.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) { request(3); }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("Value = " + value);
                if (value==3) cancel(); }

            @Override
            protected void hookOnComplete() { System.out.println("Complete!!"); }

            @Override
            protected void hookOnError(Throwable throwable) { super.hookOnError(throwable); }

            @Override
            protected void hookOnCancel() { super.hookOnComplete(); }
        });
    }

    @Test
    public void testBackPresssureDrop() {
        var numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("integer = " + integer));
        numbers
            .onBackpressureDrop(integer -> {
                System.out.println("Dropped value = " + integer);
            })
            .subscribe(new BaseSubscriber<Integer>() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(3);
                }

                @Override
                protected void hookOnNext(Integer value) {
                    System.out.println("Value = " + value);
                    if (value==3) hookOnCancel();
                }

                @Override
                protected void hookOnComplete() {
                    System.out.println("Complete!!");
                }

                @Override
                protected void hookOnError(Throwable throwable) {
                    super.hookOnError(throwable);
                }

                @Override
                protected void hookOnCancel() {
                    super.hookOnComplete();
                }
            });
    }

    @Test
    public void testBackPresssureBuffer() {
        var numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("integer = " + integer));
        numbers
            .onBackpressureBuffer( 10, i -> System.out.println("Buffered value = " + i))
            .subscribe(new BaseSubscriber<Integer>() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(3);
                }

                @Override
                protected void hookOnNext(Integer value) {
                    System.out.println("Value = " + value);
                    if (value==3) hookOnCancel();
                }

                @Override
                protected void hookOnComplete() {
                    System.out.println("Complete!!");
                }

                @Override
                protected void hookOnError(Throwable throwable) {
                    super.hookOnError(throwable);
                }

                @Override
                protected void hookOnCancel() {
                    super.hookOnComplete();
                }
            });
    }

    @Test
    public void testBackPresssureError() {
        var numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("integer = " + integer));
        numbers
            .onBackpressureError()
            .subscribe(new BaseSubscriber<Integer>() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(3);
                }

                @Override
                protected void hookOnNext(Integer value) {
                    System.out.println("Value = " + value);
                    if (value==3) hookOnCancel();
                }

                @Override
                protected void hookOnComplete() {
                    System.out.println("Complete!!");
                }

                @Override
                protected void hookOnError(Throwable throwable) {
                    System.out.println("Throwable = " + throwable);
                }

                @Override
                protected void hookOnCancel() {
                    super.hookOnComplete();
                }
            });
    }
}
