package ru.otus.hw02;

import ru.otus.hw02.exception.ModuleException;
import ru.otus.hw02.service.ExamService;
import ru.otus.hw02.service.statistic.Statistic;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) throws ModuleException {
        try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml")) {
            ExamService examService = context.getBean(ExamService.class);
            Statistic statistic = examService.askQuestions();
            examService.printResults(statistic);
        }
    }
}
