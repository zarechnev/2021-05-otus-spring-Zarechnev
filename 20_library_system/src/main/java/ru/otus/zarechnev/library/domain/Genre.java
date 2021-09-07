package ru.otus.zarechnev.library.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document(collection = "genre")
public class Genre {

    @Id
    private String id;

    private String name;
}