package ru.otus.zarechnev.tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.zarechnev.tester.question.QuestionParser;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionParser questionParser = context.getBean(QuestionParser.class);
        questionParser.getTestQuestionItems()
                .forEach(question ->  System.out.println(question.getQuestion()));
        context.close();
    }
}