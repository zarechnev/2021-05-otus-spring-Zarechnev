package ru.otus.zarechnev.library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Comment;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.repository.BookRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "setUp", author = "Albus Percival Wulfric Brian Dumbledore", runAlways = false)
    public void setUp(BookRepository bookRepository) {
        bookRepository.save(new Book()
                .setTitle("Станцио́нный смотри́тель")
                .setAuthor(new Author().setName("Александр Сергеевич Пушкин"))
                .setGenre(new Genre().setName("повесть"))
                .setComments(List.of(new Comment().setCommentText("ну крутяк же!!!")))
        );

        bookRepository.save(new Book()
                .setTitle("Кладбище бездомных животных")
                .setAuthor(new Author().setName("Никита Телогрейкин"))
                .setGenre(new Genre().setName("басня"))
                .setComments(List.of(new Comment().setCommentText("аще чума...")))
        );
    }
}
