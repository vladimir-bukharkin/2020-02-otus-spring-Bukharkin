package ru.otus.hw01.service;

import ru.otus.hw01.dao.QuestionLDao;
import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.service.statistic.Statistic;
import ru.otus.hw01.service.statistic.StatisticFactory;
import ru.otus.hw01.view.result.QuestionView;

import java.util.List;

public class DefaultQAService implements QAService {

    private final QuestionView view;
    private final QuestionLDao dao;
    private final StatisticFactory statisticFactory;

    public DefaultQAService(QuestionLDao dao, QuestionView view, StatisticFactory statisticFactory) {
        this.view = view;
        this.statisticFactory = statisticFactory;
        this.dao = dao;
    }

    @Override
    public Statistic askQuestions() {
        List<Question> questions = dao.getAll();
        Statistic statistic = statisticFactory.createStatistic(questions.size());
        questions.forEach(question -> {
            String answer = view.ask(question);
            if (checkAnswer(question, answer)) {
                statistic.incrementCorrectAnswers();
            }
        });
        return statistic;
    }

    @Override
    public void printResults(Statistic statistic) {
        view.printResults(statistic);
    }

    @Override
    public boolean checkAnswer(Question question, String answer) {
        return question.getAnswer().equals(answer.trim().toLowerCase());
    }
}
