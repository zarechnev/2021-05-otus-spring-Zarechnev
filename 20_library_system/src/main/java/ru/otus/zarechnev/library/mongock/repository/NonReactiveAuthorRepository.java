package ru.otus.zarechnev.library.mongock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.zarechnev.library.domain.Author;

public interface NonReactiveAuthorRepository extends MongoRepository<Author, String> {
}
