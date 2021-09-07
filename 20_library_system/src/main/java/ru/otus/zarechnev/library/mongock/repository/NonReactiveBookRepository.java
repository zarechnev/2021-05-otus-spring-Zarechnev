package ru.otus.zarechnev.library.mongock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.zarechnev.library.domain.Book;

public interface NonReactiveBookRepository extends MongoRepository<Book, String> {
}
