package com.hket.reactive.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

public class BookServiceTest {
    
    private BookInfoService bookInfoService = new BookInfoService();
    private ReviewService reviewService = new ReviewService();
    private BookService bookService = new BookService(bookInfoService, reviewService);

    @Test
    void getBooks() {
        var books = bookService.getBooks();
        StepVerifier.create(books)
            .assertNext(book -> {
                assertEquals("Book One", book.getBookinfo().getTitle());
                assertEquals( 2,book.getReviews().size());
            })
            .assertNext(book -> {
                assertEquals("Book Two", book.getBookinfo().getTitle());
                assertEquals( 2,book.getReviews().size());
            })
            .assertNext(book -> {
                assertEquals("Book Three", book.getBookinfo().getTitle());
                assertEquals( 2,book.getReviews().size());
            })
            .verifyComplete();
    }

    @Test
    void getBookById() {
        var book = bookService.getBookById(1);
        StepVerifier.create(book)
            .assertNext(b -> {
                assertEquals("Book One", b.getBookinfo().getTitle());
                assertEquals(2, b.getReviews().size());
            })
            .verifyComplete();
    }
}
