package ru.otus.hw01.service.statistic;

import javax.validation.constraints.PositiveOrZero;

public class Statistic {

    @PositiveOrZero
    private final int count;
    @PositiveOrZero
    private int correctAnswers;

    Statistic(int count) {
        this.count = count;
    }

    void incrementCorrectAnswers() {
        correctAnswers++;
    }

    public int getCount() {
        return count;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}
