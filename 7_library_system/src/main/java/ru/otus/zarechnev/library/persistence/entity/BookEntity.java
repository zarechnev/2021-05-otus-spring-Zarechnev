package ru.otus.zarechnev.library.persistence.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookEntity {
    private Long id;
    private String title;
    private Long genreId;
    private Long authorId;
}
