package ru.otus.hw01.service;

import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.service.statistic.StatisticService;

public interface QAService {

    void askNextQuestion();

    void printResults();

    boolean hasNextQuestion();

    boolean checkAnswer(Question question, String answer);

    StatisticService getStatisticService();
}
