package ru.otus.zarechnev.library.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Genre {
    private Long id;
    private String name;
}