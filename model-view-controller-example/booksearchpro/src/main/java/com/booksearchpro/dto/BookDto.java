package com.booksearchpro.dto;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record BookDto(String isbn, String title, String author) {
}
