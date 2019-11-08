package ru.otus.hw03.service.interaction;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.hw03.dao.QuestionDao;
import ru.otus.hw03.exception.ModuleException;
import ru.otus.hw03.exception.QuestionsEmptyException;
import ru.otus.hw03.question.Question;
import ru.otus.hw03.question.QuestionType;
import ru.otus.hw03.service.ExamService;
import ru.otus.hw03.service.statistic.Statistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamServiceTest {

    @MockBean
    private QuestionDao questionDao;

    @MockBean
    private ExamInteractionService examInteractionService;

    @Autowired
    private ExamService examService;

    @Test
    public void askQuestionsTest() throws ModuleException {
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

    @Test(expected = QuestionsEmptyException.class)
    public void askWithoutQuestionsTest() throws ModuleException {
        Mockito.when(questionDao.getAll()).then(inv -> Collections.emptyList());
        examService.askQuestions();
    }

    @Test(expected = QuestionsEmptyException.class)
    public void askNullQuestionsTest() throws ModuleException {
        Mockito.when(questionDao.getAll()).then(inv -> Collections.emptyList());
        examService.askQuestions();
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
