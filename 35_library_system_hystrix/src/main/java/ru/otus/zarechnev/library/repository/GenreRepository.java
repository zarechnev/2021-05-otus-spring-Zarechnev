package ru.otus.zarechnev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.zarechnev.library.domain.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findFirstByName(String name);
}
