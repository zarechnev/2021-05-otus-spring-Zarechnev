package ru.otus.zarechnev.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.zarechnev.library.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
