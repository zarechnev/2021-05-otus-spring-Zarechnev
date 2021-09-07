package ru.otus.zarechnev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.zarechnev.library.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
