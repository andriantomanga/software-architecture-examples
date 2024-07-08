package com.booksearchpro.repository;

import com.booksearchpro.model.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository extends ElasticsearchRepository<Book, String> {
    Mono<Book> findByIsbn(String isbn);

    Flux<Book> findByTitle(String title);

    Flux<Book> findByAuthor(String author);
}
