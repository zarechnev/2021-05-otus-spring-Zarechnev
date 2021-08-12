package ru.otus.zarechnev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.zarechnev.library.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
