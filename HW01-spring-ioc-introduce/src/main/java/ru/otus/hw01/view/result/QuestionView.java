package ru.otus.hw01.view.result;

import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.service.statistic.StatisticService;

public interface QuestionView {

    String ask(Question question);

    void printResults(StatisticService statisticService);
}
