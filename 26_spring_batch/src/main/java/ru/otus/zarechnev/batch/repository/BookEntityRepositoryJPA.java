package ru.otus.zarechnev.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.zarechnev.batch.domain.BookEntity;

public interface BookEntityRepositoryJPA extends JpaRepository<BookEntity, Long> {
}
