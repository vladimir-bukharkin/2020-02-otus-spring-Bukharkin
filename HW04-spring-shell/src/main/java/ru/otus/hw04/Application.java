package ru.otus.hw04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.hw04.exception.ModuleException;
import ru.otus.hw04.service.ExamService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws ModuleException {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		ExamService examService = context.getBean(ExamService.class);
		examService.askQuestions();
	}
}
