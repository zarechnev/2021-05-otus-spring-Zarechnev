package ru.otus.zarechnev.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.zarechnev.library.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
