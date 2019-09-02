package ru.otus.hw01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw01.service.QAService;
import ru.otus.hw01.service.statistic.Statistic;

public class Main {

    public static void main(String[] args) {
        try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml")) {
            QAService qaService = context.getBean(QAService.class);
            Statistic statistic = qaService.askQuestions();
            qaService.printResults(statistic);
        }
    }
}
