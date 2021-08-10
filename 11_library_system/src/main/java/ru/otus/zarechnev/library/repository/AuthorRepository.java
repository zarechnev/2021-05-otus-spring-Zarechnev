package ru.otus.zarechnev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.zarechnev.library.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
