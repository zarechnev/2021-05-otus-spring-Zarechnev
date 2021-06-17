package ru.otus.zarechnev.tester.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.LinkedList;
import java.util.List;

@Data
@Accessors(chain = true)
public class StudentAnswer {
    private String questionId;
    private List<Integer> checkedAnswers = new LinkedList<>();
}
