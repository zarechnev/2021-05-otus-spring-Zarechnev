package ru.otus.zarechnev.tester.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QuestionAnswer {
    private String answer;
    private boolean isCorrect;
}
