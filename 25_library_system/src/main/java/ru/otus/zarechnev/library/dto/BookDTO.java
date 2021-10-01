package ru.otus.zarechnev.library.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String genre;
}
