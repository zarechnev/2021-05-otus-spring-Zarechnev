package ru.otus.zarechnev.library.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.zarechnev.library.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
