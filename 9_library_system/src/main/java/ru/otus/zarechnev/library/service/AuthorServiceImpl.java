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
    private final UserCommunicator userCommunicator;

    @Override
    @Transactional
    public void addAuthor() {
        String name = userCommunicator.getAnswer("New author name:");
        authorRepository.insert(new Author().setName(name));
    }
}
