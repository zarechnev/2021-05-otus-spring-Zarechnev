package ru.otus.zarechnev.library.service;

import com.mongodb.client.DistinctIterable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class GenreServiceImpl implements GenreService {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Genre> findAll() {
        List<Genre> list = new ArrayList<>();

        DistinctIterable<String> distinct = mongoTemplate
                .getCollection(mongoTemplate.getCollectionName(Book.class))
                .distinct("genre.name", String.class);

        for (String s : distinct) {
            list.add(new Genre().setName(s));
        }

        return list;
    }
}
