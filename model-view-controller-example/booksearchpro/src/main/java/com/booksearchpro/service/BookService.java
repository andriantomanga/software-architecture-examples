package com.booksearchpro.service;

import com.booksearchpro.dto.BookDto;
import com.booksearchpro.exception.BookNotFoundException;
import com.booksearchpro.mapper.BookMapper;
import com.booksearchpro.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public Mono<BookDto> saveBook(BookDto bookDto) {
        var book = bookRepository.save(bookMapper.toEntity(bookDto));
        return Mono.just(bookMapper.toDto(book));
    }

    public Mono<BookDto> findBookByIsbn(String isbn) throws BookNotFoundException {
        return bookRepository.findByIsbn(isbn)
                .switchIfEmpty(Mono.error(new BookNotFoundException("Le livre ayant l'ISBN " + isbn + " est introuvable")))
                .map(bookMapper::toDto);
    }

    public Flux<BookDto> findBookByAuthor(String author) throws BookNotFoundException {
        return bookRepository.findByAuthor(author)
                .switchIfEmpty(Mono.error(new BookNotFoundException("Aucun livre dont l'auteur est " + author + " n'a été trouvé")))
                .map(bookMapper::toDto);
    }
}
