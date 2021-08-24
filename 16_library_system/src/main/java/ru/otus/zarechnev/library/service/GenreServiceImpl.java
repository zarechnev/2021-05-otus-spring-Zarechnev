package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.repository.GenreRepository;

@Service
@RequiredArgsConstructor
class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public Genre findFirstByNameOrCreateAndGet(String name) {
        return genreRepository.findFirstByName(name)
                .orElseGet(() -> genreRepository.save(new Genre().setName(name)));
    }
}
