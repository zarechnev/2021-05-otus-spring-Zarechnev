package ru.otus.zarechnev.library.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.dto.BookDTO;
import ru.otus.zarechnev.library.repository.AuthorRepository;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(value = {
        AuthorServiceImpl.class,
        GenreServiceImpl.class,
        BookServiceImpl.class
})
class LibraryServiceImplIntegTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookServiceImpl bookService;

    @Test
    void edit() {
        // given
        BookDTO bookDTO = new BookDTO().setId(1L).setAuthor("aa").setGenre("gg").setTitle("tt");

        // when
        Book actual = bookService.saveFromDto(bookDTO);

        // then
        assertEquals(
                new Book().setId(1L)
                        .setTitle("tt")
                        .setGenre(new Genre().setId(3L).setName("gg"))
                        .setAuthor(new Author().setId(3L).setName("aa"))
                        .setComments(Collections.emptyList()),
                actual
        );
    }

    @Test
    void save() {
        // given
        BookDTO bookDTO = new BookDTO().setAuthor("aa").setGenre("gg").setTitle("tt");

        // when
        Book actual = bookService.saveFromDto(bookDTO);

        // then
        assertEquals(
                new Book().setId(3L)
                        .setTitle("tt")
                        .setGenre(new Genre().setId(4L).setName("gg"))
                        .setAuthor(new Author().setId(4L).setName("aa")),
                actual
        );
    }

}