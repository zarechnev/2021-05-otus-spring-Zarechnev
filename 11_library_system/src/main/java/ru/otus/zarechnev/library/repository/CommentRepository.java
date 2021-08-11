package ru.otus.zarechnev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.zarechnev.library.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
