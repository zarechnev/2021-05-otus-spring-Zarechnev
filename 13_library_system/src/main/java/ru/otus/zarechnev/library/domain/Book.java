package ru.otus.zarechnev.library.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Accessors(chain = true)
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String title;

    private Genre genre;

    private Author author;

    private List<Comment> comments;
}
