package com.booksearchpro.service;

import com.booksearchpro.dto.BookDto;
import com.booksearchpro.exception.BookNotFoundException;
import com.booksearchpro.mapper.BookMapper;
import com.booksearchpro.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class BookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public Mono<BookDto> saveBook(BookDto bookDto) {
        LOGGER.info("Saving a book with {} ISBN", bookDto.isbn());
        var book = bookRepository.save(bookMapper.toEntity(bookDto));
        return Mono.just(bookMapper.toDto(book));
    }

    public Mono<BookDto> findBookByIsbn(String isbn) throws BookNotFoundException {
        LOGGER.info("Finding the book whose ISBN is {}", isbn);
        return Mono.defer(() -> bookRepository.findByIsbn(isbn)
                        .map(book -> Mono.just(bookMapper.toDto(book)))
                        .orElseGet(() -> Mono.error(new BookNotFoundException("The book with the ISBN " + isbn + " was not found"))))
                .doOnError(e -> LOGGER.error("Error finding book with ISBN {}", isbn, e));
    }

    public Flux<BookDto> findBookByAuthor(String author) throws BookNotFoundException {
        LOGGER.info("Finding for all books written by {}", author);
        return Flux.defer(() -> {
                    var foundBooks = bookRepository.findByAuthor(author);
                    if (foundBooks.isEmpty()) {
                        return Flux.error(new BookNotFoundException("No books written by " + author + " were found"));
                    }
                    return Flux.fromIterable(bookMapper.toDtos(foundBooks));
                })
                .doOnError(e -> LOGGER.error("Error finding books by author {}", author, e));
    }
}
