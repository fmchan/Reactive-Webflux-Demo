package com.hket.reactive.service;

import java.util.List;

import com.hket.reactive.model.BookInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookInfoService {
    
    public Flux<BookInfo> getBooks(){
        var books = List.of(
            new BookInfo(1, "Book One", "Author One", "1111111111"),
            new BookInfo(2, "Book Two", "Author two", "2222222222"),
            new BookInfo(3, "Book Three", "Author three", "3333333333")
        );

        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookId) {
        var book = new BookInfo(bookId, "Book One", "Author One", "1111111111");
        return Mono.just(book);
    }

}
