package ru.otus.hw01.service.statistic;

import ru.otus.hw01.exception.ModuleException;

public class DefaultStatisticFactory implements StatisticFactory {

    @Override
    public Statistic createStatistic(int questionsCount) throws ModuleException {
        return new DefaultStatistic(questionsCount);
    }
}
