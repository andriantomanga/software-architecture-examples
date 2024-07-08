package com.booksearchpro.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    private String id;      // identifiant technique (a ne jamais exposer au public)
    private String isbn;    // identifiant fonctionnel (pouvant etre expose)
    private String title;
    private String author;
}
