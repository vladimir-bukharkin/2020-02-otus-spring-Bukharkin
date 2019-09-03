package ru.otus.hw01.service.statistic;

import ru.otus.hw01.exception.ModuleException;

public interface StatisticFactory {

    Statistic createStatistic(int questionsCount) throws ModuleException;
}
