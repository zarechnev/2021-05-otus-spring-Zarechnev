package ru.otus.zarechnev.library.service;

import org.springframework.stereotype.Service;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
class ConsoleUserCommunicator implements UserCommunicator {

    @Override
    public Book getNewBook() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Title:");
            String title = reader.readLine();

            System.out.println("Genre:");
            String genre = reader.readLine();

            System.out.println("Author:");
            String author = reader.readLine();

            return new Book()
                    .setTitle(title)
                    .setAuthor(new Author().setName(author))
                    .setGenre(new Genre().setName(genre));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getEditedBook(Book book) {
        Book newBook = getNewBook();
        return newBook.setId(book.getId());
    }
}
