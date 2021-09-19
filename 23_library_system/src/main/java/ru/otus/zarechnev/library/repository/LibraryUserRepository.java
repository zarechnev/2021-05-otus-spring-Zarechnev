package ru.otus.zarechnev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.zarechnev.library.domain.LibraryUser;

import java.util.Optional;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {
    Optional<LibraryUser> findFirstByLogin(String login);
}
