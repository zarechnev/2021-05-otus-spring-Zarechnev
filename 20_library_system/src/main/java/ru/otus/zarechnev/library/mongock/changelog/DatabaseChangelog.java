package ru.otus.zarechnev.library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Comment;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.mongock.repository.NonReactiveAuthorRepository;
import ru.otus.zarechnev.library.mongock.repository.NonReactiveBookRepository;
import ru.otus.zarechnev.library.mongock.repository.NonReactiveGenreRepository;
import ru.otus.zarechnev.library.repository.AuthorRepository;
import ru.otus.zarechnev.library.repository.GenreRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "080", id = "setUpGenres", author = "Albus Percival Wulfric Brian Dumbledore", runAlways = false)
    public void setUpGenres(NonReactiveGenreRepository genreRepository) {
        genreRepository.save(new Genre().setName("повесть"));
        genreRepository.save(new Genre().setName("басня"));
    }

    @ChangeSet(order = "090", id = "setUpAuthors", author = "Albus Percival Wulfric Brian Dumbledore", runAlways = false)
    public void setUpAuthors(NonReactiveAuthorRepository authorRepository) {
        authorRepository.save(new Author().setName("Александр Сергеевич Пушкин"));
        authorRepository.save(new Author().setName("Никита Телогрейкин"));
    }

    @ChangeSet(order = "100", id = "setUpBooks", author = "Albus Percival Wulfric Brian Dumbledore", runAlways = false)
    public void setUpBooks(NonReactiveBookRepository bookRepository,
                           GenreRepository genreRepository,
                           AuthorRepository authorRepository) {
        bookRepository.save(new Book()
                .setTitle("Станцио́нный смотри́тель")
                .setAuthor(authorRepository.findAll().blockFirst())
                .setGenre(genreRepository.findAll().blockLast())
                .setComments(List.of(new Comment().setCommentText("ну крутяк же!!!")))
        );

        bookRepository.save(new Book()
                .setTitle("Кладбище бездомных животных")
                .setAuthor(authorRepository.findAll().blockLast())
                .setGenre(genreRepository.findAll().blockFirst())
                .setComments(List.of(new Comment().setCommentText("аще чума...")))
        );
    }
}
