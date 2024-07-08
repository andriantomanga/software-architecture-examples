package com.booksearchpro.mapper;

import com.booksearchpro.dto.BookDto;
import com.booksearchpro.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Book toEntity(BookDto bookDto);
}
