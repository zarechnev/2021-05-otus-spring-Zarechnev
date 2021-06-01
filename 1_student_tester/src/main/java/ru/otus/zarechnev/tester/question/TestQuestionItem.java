package ru.otus.zarechnev.tester.question;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.LinkedList;
import java.util.List;

@Data
@Accessors(chain = true)
public class TestQuestionItem {
    private String question;
    private List<Answer> answers = new LinkedList<>();
}

@Data
@Accessors(chain = true)
class Answer {
    private String answer;
    private boolean isCorrect;
}
