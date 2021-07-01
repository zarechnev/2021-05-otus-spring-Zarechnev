package ru.otus.zarechnev.library.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Book {
    private Long id;
    private String title;
    private Genre genre;
    private Author author;
}
