package ru.otus.zarechnev.batch.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.zarechnev.batch.domain.BookDocument;

public interface BookDocumentRepositoryMongo extends MongoRepository<BookDocument, String> {
}
