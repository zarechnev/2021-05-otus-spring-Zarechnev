package ru.otus.zarechnev.tester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.zarechnev.tester.tester.Tester;
import ru.otus.zarechnev.tester.tester.WithFileConsoleTester;

@SpringBootApplication
public class TesterApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TesterApplication.class, args);
		Tester withFileConsoleTester = context.getBean(WithFileConsoleTester.class);
		withFileConsoleTester.startTest();
	}

}
