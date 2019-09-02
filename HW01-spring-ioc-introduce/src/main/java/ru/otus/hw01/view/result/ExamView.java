package ru.otus.hw01.view.result;

import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.service.statistic.Statistic;

public interface ExamView {

    String ask(Question question);

    void printResults(Statistic statistic);
}
