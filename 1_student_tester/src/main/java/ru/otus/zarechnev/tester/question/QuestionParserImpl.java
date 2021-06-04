package ru.otus.zarechnev.tester.question;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
class QuestionParserImpl implements QuestionParser {
    public static final String DELIMITER = ";";

    private final String questionFileName;

    @Override
    public List<TestQuestionItem> getTestQuestionItems() {
        List<TestQuestionItem> testQuestionItems = new ArrayList<>();

        try {
            InputStream inputStream = new ClassPathResource(questionFileName).getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while((line = br.readLine()) != null) {
                Queue<String> queue = new LinkedList<>(Arrays.asList(line.split(DELIMITER)));

                TestQuestionItem testQuestionItem = new TestQuestionItem()
                        .setQuestion(queue.poll());

                while (!queue.isEmpty()) {
                    Answer answer = new Answer()
                            .setAnswer(queue.poll())
                            .setCorrect(Boolean.parseBoolean(queue.poll()));
                    testQuestionItem.getAnswers().add(answer);
                }

                testQuestionItems.add(testQuestionItem);
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return testQuestionItems;
    }
}
