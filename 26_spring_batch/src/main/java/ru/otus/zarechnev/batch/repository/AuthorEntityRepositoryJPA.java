package ru.otus.zarechnev.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.zarechnev.batch.domain.AuthorEntity;

public interface AuthorEntityRepositoryJPA extends JpaRepository<AuthorEntity, Long> {
}
