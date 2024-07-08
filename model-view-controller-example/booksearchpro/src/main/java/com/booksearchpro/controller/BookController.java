package com.booksearchpro.controller;

import com.booksearchpro.dto.BookDto;
import com.booksearchpro.exception.BookNotFoundException;
import com.booksearchpro.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public Mono<BookDto> saveBook(@RequestBody BookDto book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/{isbn}")
    public Mono<BookDto> findBookByIsbn(@PathVariable String isbn) throws BookNotFoundException {
        return bookService.findBookByIsbn(isbn);
    }

    @GetMapping("/{author}")
    public Flux<BookDto> findBookByAuthor(@PathVariable String author) throws BookNotFoundException {
        return bookService.findBookByAuthor(author);
    }
}
