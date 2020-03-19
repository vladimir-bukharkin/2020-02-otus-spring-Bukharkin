package ru.otus.hw03.service.interaction;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw03.LocalizationServiceTestBean;
import ru.otus.hw03.exception.ModuleException;
import ru.otus.hw03.question.Question;
import ru.otus.hw03.question.QuestionType;
import ru.otus.hw03.service.i18n.LocalizationService;
import ru.otus.hw03.service.io.IOStringService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ExamConsoleInteractionTest {

	@MockBean
	IOStringService ioStringService;

	@Autowired
	private ExamInteractionService examInteractionService;

	@TestConfiguration
	static class TestConfig {
		@Bean
		public LocalizationService localization() {
			return new LocalizationServiceTestBean("questions/questions_ru.csv");
		}
	}

	@Test
	@DisplayName("Проверяет, что getAll() возвращает правильные значения")
	void testAskSingleQuestion() throws ModuleException {
		String expectedOutput = "some question\n" +
				"question.single.correct.answer\n" +
				"fill.answer.single...";
		Mockito.doAnswer(invocation -> {
			String result = invocation.getArgument(0);
			Assertions.assertThat(result).isEqualTo(expectedOutput);
			return null;
		}).when(ioStringService).write(Mockito.anyString(), Mockito.any());
		System.out.println(examInteractionService.ask(new Question(1,"some question","some answer", QuestionType.SINGLE)));
		Mockito.verify(ioStringService).write(Mockito.anyString(), Mockito.any());
	}
}
