package com.booksearchpro.service;

import com.booksearchpro.dto.BookDto;
import com.booksearchpro.exception.BookNotFoundException;
import com.booksearchpro.mapper.BookMapper;
import com.booksearchpro.model.Book;
import com.booksearchpro.repository.BookRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Disabled
class BookServiceTest {
    private static final String GIVEN_ISBN = "978-0134685991";
    private static final String GIVEN_TITLE = "Java Programming";
    private static final String GIVEN_AUTHOR = "NABIL Andriantomanga";

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService sut;

    @Test
    void should_correctly_save_book() {
        // Given
        var bookDto = givenBookDto();
        var bookEntity = givenBookEntity();
        when(bookRepository.save(any())).thenReturn(bookEntity);
        when(bookMapper.toDto(bookEntity)).thenReturn(bookDto);
        when(bookMapper.toEntity(any())).thenReturn(bookEntity);

        // When
        var savedBook = sut.saveBook(bookDto);

        // Then
        StepVerifier.create(savedBook)
                .expectNext(bookDto)
                .verifyComplete();
    }

    @Test
    void should_correctly_find_book_by_isbn() {
        // Given
        var bookDto = givenBookDto();
        var bookEntity = givenBookEntity();
        when(bookRepository.findByIsbn(GIVEN_ISBN)).thenReturn(Optional.of(bookEntity));
        when(bookMapper.toDto(bookEntity)).thenReturn(bookDto);

        // When
        var result = sut.findBookByIsbn(GIVEN_ISBN);

        // Then
        StepVerifier.create(result)
                .expectNextMatches(dto -> GIVEN_ISBN.equals(dto.isbn()) &&
                        GIVEN_TITLE.equals(dto.title()) &&
                        GIVEN_AUTHOR.equals(dto.author()))
                .verifyComplete();

        verify(bookRepository, times(1)).findByIsbn(GIVEN_ISBN);
        verify(bookMapper, times(1)).toDto(bookEntity);
    }

    @Test
    void should_correctly_find_book_by_author() {
        // Given
        var bookDto = givenBookDto();
        var bookEntity = givenBookEntity();
        when(bookRepository.findByAuthor(GIVEN_AUTHOR)).thenReturn(List.of(bookEntity));
        when(bookMapper.toDtos(any())).thenReturn(List.of(bookDto));

        // When
        var result = sut.findBookByAuthor(GIVEN_AUTHOR);

        // Then
        StepVerifier.create(result)
                .expectNextMatches(dto -> GIVEN_ISBN.equals(dto.isbn()) &&
                        GIVEN_TITLE.equals(dto.title()) &&
                        GIVEN_AUTHOR.equals(dto.author()))
                .verifyComplete();

        verify(bookRepository, times(1)).findByAuthor(GIVEN_AUTHOR);
        verify(bookMapper, times(1)).toDtos(any());
    }

    @Test
    void should_throw_exception_when_find_book_by_isbn_and_book_not_found() {
        // Given
        when(bookRepository.findByIsbn(GIVEN_ISBN)).thenReturn(Optional.empty());

        // When
        var result = sut.findBookByIsbn(GIVEN_ISBN);

        // Then
        StepVerifier.create(result)
                .expectError(BookNotFoundException.class)
                .verify();
    }

    @Test
    void should_throw_exception_when_find_book_by_author_and_book_not_found() {
        // Given
        when(bookRepository.findByAuthor(GIVEN_AUTHOR)).thenReturn(Collections.emptyList());

        // When
        var result = sut.findBookByAuthor(GIVEN_AUTHOR);

        // Then
        StepVerifier.create(result)
                .expectError(BookNotFoundException.class)
                .verify();
    }

    private Book givenBookEntity() {
        var book = new Book();
        book.setIsbn(GIVEN_ISBN);
        book.setTitle(GIVEN_TITLE);
        book.setAuthor(GIVEN_AUTHOR);
        return book;
    }

    private BookDto givenBookDto() {
        return BookDto.builder()
                .isbn(GIVEN_ISBN)
                .title(GIVEN_TITLE)
                .author(GIVEN_AUTHOR)
                .build();
    }
}