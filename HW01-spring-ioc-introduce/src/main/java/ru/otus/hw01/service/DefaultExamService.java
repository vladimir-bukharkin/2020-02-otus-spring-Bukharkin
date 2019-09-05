package ru.otus.hw01.service;

import ru.otus.hw01.dao.QuestionCsvDao;
import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.exception.ModuleException;
import ru.otus.hw01.interaction.ExamInteractionService;
import ru.otus.hw01.service.statistic.Statistic;

import java.util.List;

public class DefaultExamService implements ExamService {

    private final QuestionCsvDao dao;
    private final ExamInteractionService interactionService;

    public DefaultExamService(QuestionCsvDao dao, ExamInteractionService interactionService) {
        this.interactionService = interactionService;
        this.dao = dao;
    }

    @Override
    public Statistic askQuestions() throws ModuleException {
        List<Question> questions = dao.getAll();
        Statistic statistic = new Statistic(questions.size());
        questions.forEach(question -> {
            String answer = interactionService.ask(question);
            if (checkAnswer(question, answer)) {
                statistic.incrementCorrectAnswers();
            }
        });
        return statistic;
    }

    @Override
    public void printResults(Statistic statistic) {
        interactionService.printResults(statistic);
    }

    @Override
    public boolean checkAnswer(Question question, String answer) {
        return question.getAnswer().equals(answer.trim().toLowerCase());
    }
}
