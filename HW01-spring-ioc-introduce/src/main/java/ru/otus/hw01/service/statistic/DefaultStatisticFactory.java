package ru.otus.hw01.service.statistic;

public class DefaultStatisticFactory implements StatisticFactory {

    @Override
    public Statistic createStatistic(int questionsCount) {
        return new DefaultStatistic(questionsCount);
    }
}
