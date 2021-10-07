package ru.otus.zarechnev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.zarechnev.library.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findFirstByName(String name);
}
