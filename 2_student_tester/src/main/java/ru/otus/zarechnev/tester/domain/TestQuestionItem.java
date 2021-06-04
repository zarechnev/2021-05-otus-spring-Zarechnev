package ru.otus.zarechnev.tester.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.LinkedList;
import java.util.List;

@Data
@Accessors(chain = true)
public class TestQuestionItem {
    private String questionId;
    private String question;
    private List<QuestionAnswer> questionAnswers = new LinkedList<>();
}

