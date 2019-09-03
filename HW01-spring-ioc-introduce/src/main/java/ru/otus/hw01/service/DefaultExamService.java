package ru.otus.hw01.service;

import ru.otus.hw01.dao.QuestionLDao;
import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.exception.ModuleException;
import ru.otus.hw01.service.statistic.Statistic;
import ru.otus.hw01.service.statistic.StatisticFactory;
import ru.otus.hw01.view.ExamView;

import java.util.List;

public class DefaultExamService implements ExamService {

    private final QuestionLDao dao;
    private final ExamView view;
    private final StatisticFactory statisticFactory;

    public DefaultExamService(QuestionLDao dao, ExamView view, StatisticFactory statisticFactory) {
        this.view = view;
        this.statisticFactory = statisticFactory;
        this.dao = dao;
    }

    @Override
    public Statistic askQuestions() throws ModuleException {
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
