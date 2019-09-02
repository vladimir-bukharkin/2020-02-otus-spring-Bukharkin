package ru.otus.hw01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw01.service.QAService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QAService qaService = context.getBean(QAService.class);
        while (qaService.hasNextQuestion()) {
            qaService.askNextQuestion();
        }
        qaService.printResults();
    }
}
