package ru.otus.hw02.service.interaction.console;

import ru.otus.hw02.domain.question.Question;
import ru.otus.hw02.service.interaction.ExamInteractionService;
import ru.otus.hw02.service.io.IOStringService;
import ru.otus.hw02.service.statistic.Statistic;
import ru.otus.hw02.service.statistic.StatisticCalculator;

public class ExamConsoleInteractionService implements ExamInteractionService {

    private final IOStringService ioStringService;
    private final StatisticCalculator statisticCalculator;

    public ExamConsoleInteractionService(IOStringService ioStringService, StatisticCalculator statisticCalculator) {
        this.ioStringService = ioStringService;
        this.statisticCalculator = statisticCalculator;
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
        ioStringService.write("Результаты тестирования: " +
                "\nВсего вопросов: " + statistic.getQuestionsCount() +
                "\nПравильных ответов: " + statistic.getCorrectAnswersCount() +
                "\nИтоговый балл: " + statisticCalculator.getResultMark(statistic), System.out);
    }

    private void askSingleQuestion(Question question) {
        ioStringService.write(question.getQuestion() +
                "\nВопрос с единственным вариантом ответа" +
                "\nВведите номер правильного варианта ответа...", System.out);
    }

    private void askMultiplyQuestion(Question question) {
        ioStringService.write(question.getQuestion() +
                "\nВопрос с несколькими вариантами ответа" +
                "\nВведите номера правильных вариантов ответа через \",\" (Пример: 1,3,5)...", System.out);
    }

    private void askTextQuestion(Question question) {
        ioStringService.write(question.getQuestion() +
                "\nВопрос c ответом в ввиде текста" +
                "\nВведите ответ словами...", System.out);
    }
}
