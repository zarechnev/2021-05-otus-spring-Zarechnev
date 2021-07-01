package ru.otus.zarechnev.library.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.persistence.dao.GenreDao;
import ru.otus.zarechnev.library.persistence.sequence.SequenceService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class GenrePersistenceServiceImpl implements GenrePersistenceService {

    private final GenreDao genreDao;
    private final SequenceService sequenceService;

    @Override
    public Long checkIfGenreExistOrCreateAndGetId(String genreName) {
        Optional<Genre> existingGenre = genreDao.getAll().stream()
                .filter(g -> g.getName().equalsIgnoreCase(genreName))
                .findAny();

        if (existingGenre.isPresent()) {
            return existingGenre.get().getId();
        }

        Genre newGenre = new Genre()
                .setId(sequenceService.getNewGenreId())
                .setName(genreName);

        genreDao.insert(newGenre);

        return newGenre.getId();
    }
}
