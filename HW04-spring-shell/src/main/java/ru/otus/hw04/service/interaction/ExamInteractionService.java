package ru.otus.hw04.service.interaction;

import ru.otus.hw04.question.Question;
import ru.otus.hw04.service.statistic.Statistic;

public interface ExamInteractionService {

    String ask(Question question);

    void printResults(Statistic statistic);
}
