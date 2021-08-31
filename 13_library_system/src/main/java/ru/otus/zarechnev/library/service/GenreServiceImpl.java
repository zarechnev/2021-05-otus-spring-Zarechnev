package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
