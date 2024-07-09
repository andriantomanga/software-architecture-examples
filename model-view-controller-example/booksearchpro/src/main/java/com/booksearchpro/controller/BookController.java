package com.booksearchpro.controller;

import com.booksearchpro.dto.BookDto;
import com.booksearchpro.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BookDto> saveBook(@RequestBody BookDto book) {
        return bookService.saveBook(book);
    }

    @GetMapping(value = "/isbn/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BookDto> findBookByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn(isbn)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found: " + isbn)));
    }

    @GetMapping(value = "/author/{author}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<BookDto> findBookByAuthor(@PathVariable String author) {
        return bookService.findBookByAuthor(author)
                .switchIfEmpty(Flux.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Books written by " + author + " not found")));
    }
}
