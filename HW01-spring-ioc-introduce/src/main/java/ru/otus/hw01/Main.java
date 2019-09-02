package ru.otus.hw01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw01.service.ExamService;
import ru.otus.hw01.service.statistic.Statistic;

public class Main {

    public static void main(String[] args) {
        try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml")) {
            ExamService examService = context.getBean(ExamService.class);
            Statistic statistic = examService.askQuestions();
            examService.printResults(statistic);
        }
    }
}
