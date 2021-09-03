package ru.otus.zarechnev.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.zarechnev.library.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
