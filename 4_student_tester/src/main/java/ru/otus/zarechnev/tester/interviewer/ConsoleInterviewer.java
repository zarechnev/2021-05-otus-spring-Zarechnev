package ru.otus.zarechnev.tester.interviewer;

import org.springframework.stereotype.Component;
import ru.otus.zarechnev.tester.domain.StudentAnswer;
import ru.otus.zarechnev.tester.domain.TestQuestionItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
class ConsoleInterviewer implements Interviewer {

    private static final String ANSWER_SPLITTER = ",";

    @Override
    public StudentAnswer askAndGetAnswer(TestQuestionItem testQuestionItem) {
        askQuestion(testQuestionItem);
        return parseAnswer().setQuestionId(testQuestionItem.getQuestionId());
    }

    private void askQuestion(TestQuestionItem question) {
        System.out.println(question.getQuestion());

        for (int i = 0; i < question.getQuestionAnswers().size(); i++) {
            System.out.printf("%d) %s\n", i + 1, question.getQuestionAnswers().get(i).getAnswer());
        }
    }

    private StudentAnswer parseAnswer() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            String[] split = line.split(ANSWER_SPLITTER);

            StudentAnswer studentAnswer = new StudentAnswer();

            for (String ans : split) {
                studentAnswer.getCheckedAnswers().add(Integer.parseInt(ans) - 1);
            }

            return studentAnswer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
