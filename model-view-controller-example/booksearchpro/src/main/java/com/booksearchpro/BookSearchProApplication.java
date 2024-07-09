package com.booksearchpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.booksearchpro.repository")
@EntityScan(basePackages = "com.booksearchpro.model")
public class BookSearchProApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookSearchProApplication.class, args);
    }

}
