package ru.otus.zarechnev.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.zarechnev.integration.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
