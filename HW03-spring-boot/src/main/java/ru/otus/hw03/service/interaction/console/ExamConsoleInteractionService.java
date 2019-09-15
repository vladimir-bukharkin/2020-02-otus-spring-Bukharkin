package ru.otus.hw03.service.interaction.console;

import org.springframework.stereotype.Service;
import ru.otus.hw03.question.Question;
import ru.otus.hw03.service.i18n.Localization;
import ru.otus.hw03.service.interaction.ExamInteractionService;
import ru.otus.hw03.service.io.IOStringService;
import ru.otus.hw03.service.statistic.Statistic;
import ru.otus.hw03.service.statistic.StatisticCalculator;

@Service
public class ExamConsoleInteractionService implements ExamInteractionService {

    private final IOStringService ioStringService;
    private final StatisticCalculator statisticCalculator;
    private final Localization localization;

    public ExamConsoleInteractionService(IOStringService ioStringService,
                                         StatisticCalculator statisticCalculator,
                                         Localization localization) {
        this.ioStringService = ioStringService;
        this.statisticCalculator = statisticCalculator;
        this.localization = localization;
    }

    @Override
    public String ask(Question question) {
        switch (question.getType()) {
            case SINGLE:
                askSingleQuestion(question);
                break;
            case MULTIPLY:
                askMultiplyQuestion(question);
                break;
            case TEXT:
                askTextQuestion(question);
                break;
            default:
                throw new InternalError("Not supported question type: " + question.getType());
        }
        return ioStringService.readLine(System.in);
    }

    @Override
    public void printResults(Statistic statistic) {
        ioStringService.write(localization.getMessage("test.results") +
                "\n" + localization.getMessage("question.amount") + ": " + statistic.getQuestionsCount() +
                "\n" + localization.getMessage("question.correct") + ": " + statistic.getCorrectAnswersCount() +
                "\n" + localization.getMessage("test.result.final.score") + ": " + statisticCalculator.getResultMark(statistic), System.out);
    }

    private void askSingleQuestion(Question question) {
        ioStringService.write(question.getQuestion() +
                "\n" + localization.getMessage("question.single.correct.answer") +
                "\n" + localization.getMessage("fill.answer.single") + "...", System.out);
    }

    private void askMultiplyQuestion(Question question) {
        ioStringService.write(question.getQuestion() +
                "\n" + localization.getMessage("question.multiply.correct.answer") +
                "\n" + localization.getMessage("fill.answer.multiply") + " \",\" (" + localization.getMessage("example") + ": 1,3,5)...", System.out);
    }

    private void askTextQuestion(Question question) {
        ioStringService.write(question.getQuestion() +
                "\n" + localization.getMessage("question.text.answer")  +
                "\n" + localization.getMessage("fill.answer.text") + "...", System.out);
    }
}
