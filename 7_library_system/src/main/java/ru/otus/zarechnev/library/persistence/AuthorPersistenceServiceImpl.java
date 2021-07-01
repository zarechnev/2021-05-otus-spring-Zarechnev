package ru.otus.zarechnev.library.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.persistence.dao.AuthorDao;
import ru.otus.zarechnev.library.persistence.sequence.SequenceService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class AuthorPersistenceServiceImpl implements AuthorPersistenceService {

    private final AuthorDao authorDao;
    private final SequenceService sequenceService;

    @Override
    public Long checkIfAuthorExistOrCreateAndGetId(String authorName) {
        Optional<Author> existingAuthor = authorDao.getAll().stream()
                .filter(a -> a.getName().equalsIgnoreCase(authorName))
                .findAny();

        if (existingAuthor.isPresent()) {
            return existingAuthor.get().getId();
        }

        Author newAuthor = new Author()
                .setId(sequenceService.getNewAuthorId())
                .setName(authorName);

        authorDao.insert(newAuthor);

        return newAuthor.getId();
    }
}
