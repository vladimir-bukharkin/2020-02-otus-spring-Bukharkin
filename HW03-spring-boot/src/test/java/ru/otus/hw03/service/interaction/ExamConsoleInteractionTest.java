package ru.otus.hw03.service.interaction;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw03.exception.ModuleException;
import ru.otus.hw03.question.Question;
import ru.otus.hw03.question.QuestionType;
import ru.otus.hw03.service.io.IOStringService;

@SpringBootTest
class ExamConsoleInteractionTest {

	@MockBean
	private IOStringService ioStringService;

	@Autowired
	private ExamInteractionService examInteractionService;

	@Test
	@DisplayName("Проверяет, что getAll() возвращает правильные значения")
	void testAskSingleQuestion() throws ModuleException {
		String expectedOutput = "some question\n" +
				"Вопрос с единственным вариантом ответа\n" +
				"Введите номер правильного варианта ответа...";
		Mockito.doAnswer(invocation -> {
			String result = invocation.getArgument(0);
			Assertions.assertThat(result).isEqualTo(expectedOutput);
			return null;
		}).when(ioStringService).write(Mockito.anyString(), Mockito.any());
		System.out.println(examInteractionService.ask(new Question(1,"some question","some answer", QuestionType.SINGLE)));
		Mockito.verify(ioStringService).write(Mockito.anyString(), Mockito.any());
	}
}
