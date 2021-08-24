package ru.otus.zarechnev.library.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.otus.zarechnev.library.domain.Book;

@Data
@Accessors(chain = true)
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String genre;

    public static BookDTO of(Book book) {
        return new BookDTO()
                .setId(book.getId())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor().getName())
                .setGenre(book.getGenre().getName());
    }
}
