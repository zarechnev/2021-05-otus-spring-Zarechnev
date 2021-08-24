package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public Author findFirstByNameOrCreateAndGet(String name) {
        return authorRepository.findFirstByName(name)
                .orElseGet(() -> authorRepository.save(new Author().setName(name)));
    }
}
