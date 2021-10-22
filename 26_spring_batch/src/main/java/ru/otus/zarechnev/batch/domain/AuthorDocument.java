package ru.otus.zarechnev.batch.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document(collection = "author")
public class AuthorDocument {

    @Id
    private String id;

    private String name;
}
