package ru.otus.zarechnev.library.service;

import com.mongodb.client.DistinctIterable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class AuthorServiceImpl implements AuthorService {

    private final MongoTemplate mongoTemplate;

    @Override
    @Transactional
    public List<Author> findAll() {
        List<Author> list = new ArrayList<>();

        DistinctIterable<String> distinct = mongoTemplate
                .getCollection(mongoTemplate.getCollectionName(Book.class))
                .distinct("author.name", String.class);

        for (String s : distinct) {
            list.add(new Author().setName(s));
        }

        return list;
    }
}