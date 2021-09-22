package ru.otus.zarechnev.library.mongock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.zarechnev.library.domain.Genre;

public interface NonReactiveGenreRepository extends MongoRepository<Genre, String> {
}
