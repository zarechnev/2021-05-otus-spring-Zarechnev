package ru.otus.zarechnev.library.mongock.testchangelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Comment;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.repository.BookRepository;

import java.util.List;

@ChangeLog
public class TestDatabaseChangelog {

    @ChangeSet(order = "001", id = "setUp", author = "author 1", runAlways = false)
    public void setUp(BookRepository bookRepository) {
        bookRepository.save(new Book()
                .setTitle("книга 1")
                .setAuthor(new Author().setName("автор 1"))
                .setGenre(new Genre().setName("жанр 1"))
                .setComments(List.of(new Comment().setCommentText("комментарий 1")))
        );

        bookRepository.save(new Book()
                .setTitle("книга 2")
                .setAuthor(new Author().setName("автор 2"))
                .setGenre(new Genre().setName("жанр 2"))
                .setComments(List.of(new Comment().setCommentText("комментарий 2")))
        );
    }
}
