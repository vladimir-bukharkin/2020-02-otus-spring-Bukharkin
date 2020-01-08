package ru.otus.hw04.service.interaction;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw04.dao.QuestionDao;
import ru.otus.hw04.exception.ModuleException;
import ru.otus.hw04.exception.QuestionsEmptyException;
import ru.otus.hw04.question.Question;
import ru.otus.hw04.question.QuestionType;
import ru.otus.hw04.service.ExamService;
import ru.otus.hw04.service.statistic.Statistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ExamServiceTest {

    @MockBean
    private QuestionDao questionDao;

    @MockBean
    private ExamInteractionService examInteractionService;

    @Autowired
    private ExamService examService;

    @Test
    void askQuestionsTest() throws ModuleException {
        Mockito.when(questionDao.getAll()).then(inv -> getTestQuestions());
        Mockito.when(examInteractionService.ask(Mockito.any(Question.class))).then(inv -> getAnswer(inv.getArgument(0)));
        AtomicReference<Statistic> statistic = new AtomicReference<>();
        Mockito.doAnswer(inv -> {
            statistic.set(inv.getArgument(0));
            return statistic;
        }).when(examInteractionService).printResults(Mockito.any(Statistic.class));

        examService.askQuestions();

        Statistic actual = statistic.get();
        Assertions.assertThat(actual.getCorrectAnswersCount()).isEqualTo(3);
        Assertions.assertThat(actual.getQuestionsCount()).isEqualTo(4);
    }

    @Test
    void askWithoutQuestionsTest() throws ModuleException {
        Mockito.when(questionDao.getAll()).then(inv -> Collections.emptyList());
        Assertions.assertThatThrownBy(() -> examService.askQuestions()).isExactlyInstanceOf(QuestionsEmptyException.class);
    }

    @Test
    void askNullQuestionsTest() throws ModuleException {
        Mockito.when(questionDao.getAll()).then(inv -> null);
        Assertions.assertThatThrownBy(() -> examService.askQuestions()).isExactlyInstanceOf(QuestionsEmptyException.class);
    }

    private List<Question> getTestQuestions() throws ModuleException {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(1, "q1", "1", QuestionType.SINGLE));
        questions.add(new Question(2, "q2", "1,2,4", QuestionType.MULTIPLY));
        questions.add(new Question(3, "q3", "answer text", QuestionType.TEXT));
        questions.add(new Question(4, "q4", "3,7", QuestionType.MULTIPLY));
        return questions;
    }

    private String getAnswer(Question question) {
        if (question.getId() == 2) {
            return "incorrect answer";
        } else {
            return question.getAnswer();
        }
    }
}
