package ru.otus.hw03.service.interaction;

import ru.otus.hw03.question.Question;
import ru.otus.hw03.service.statistic.Statistic;

public interface ExamInteractionService {

    String ask(Question question);

    void printResults(Statistic statistic);
}
