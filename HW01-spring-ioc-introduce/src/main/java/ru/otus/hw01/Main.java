package ru.otus.hw01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw01.exception.ModuleException;
import ru.otus.hw01.service.ExamService;

public class Main {

    public static void main(String[] args) throws ModuleException {
        try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml")) {
            ExamService examService = context.getBean(ExamService.class);
            examService.askQuestions();
        }
    }
}
