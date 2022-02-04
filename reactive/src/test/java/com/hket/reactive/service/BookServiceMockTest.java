package com.hket.reactive.service;

import com.hket.reactive.CustomException.BookException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class BookServiceMockTest {
    
    @Mock
    private BookInfoService bookInfoService;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private BookService bookService;

    // Join two Entity
    @Test
    void getBooksMock() {

        Mockito.when(bookInfoService.getBooks())
            .thenCallRealMethod();

        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
            .thenCallRealMethod();

        var books = bookService.getBooks();

        StepVerifier.create(books)
            .expectNextCount(3)
            .verifyComplete();

    }

    //CustomException
    @Test
    void getBooksMockOnError() {

        Mockito.when(bookInfoService.getBooks())
            .thenCallRealMethod();

        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
            .thenThrow(new IllegalStateException("Exception using test"));

        var books = bookService.getBooks();

        StepVerifier.create(books)
            .expectError(BookException.class)
            .verify();

    }

    // OnError Try 5 times
    @Test
    void getBooksMockOnErrorRetry() {

        Mockito.when(bookInfoService.getBooks())
            .thenCallRealMethod();

        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
            .thenThrow(new IllegalStateException("Exception using test"));

        var books = bookService.getBooksRetry();

        StepVerifier.create(books)
            .expectError(BookException.class)
            .verify();

    }

    // onError try 3 times with delay 1s
    @Test
    void getBooksMockOnErrorRetryWhen() {

        Mockito.when(bookInfoService.getBooks())
            .thenCallRealMethod();

        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
            .thenThrow(new IllegalStateException("Exception using test"));

        var books = bookService.getBooksRetryWhen();

        StepVerifier.create(books)
            .expectError(BookException.class)
            .verify();

    }
}
