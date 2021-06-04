package ru.otus.zarechnev.tester;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.zarechnev.tester.tester.Tester;
import ru.otus.zarechnev.tester.tester.WithFileConsoleTester;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        Tester withFileConsoleTester = context.getBean(WithFileConsoleTester.class);
        withFileConsoleTester.startTest();
    }
}