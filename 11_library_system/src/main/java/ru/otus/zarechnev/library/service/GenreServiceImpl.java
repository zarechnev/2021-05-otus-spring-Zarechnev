package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final UserCommunicator userCommunicator;

    @Override
    @Transactional
    public void addGenre() {
        String name = userCommunicator.getAnswer("New genre title:");
        genreRepository.save(new Genre().setName(name));
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
