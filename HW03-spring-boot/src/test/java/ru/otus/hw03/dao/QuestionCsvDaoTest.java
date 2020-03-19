package ru.otus.hw03.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw03.exception.ModuleException;
import ru.otus.hw03.question.Question;
import ru.otus.hw03.question.QuestionType;

@SpringBootTest
class QuestionCsvDaoTest {

	@Autowired
	private QuestionCsvDao questionCsvDao;

	@Test
	@DisplayName("Проверяет, что getAll() возвращает правильные значения")
	void testGetAll() throws ModuleException {
		System.out.println(questionCsvDao.getAll());
		Assertions.assertThat(questionCsvDao.getAll()).containsExactly(new Question(1, "Вопрос 111. Введите: \"4\"", "4", QuestionType.SINGLE),
				new Question(2, "Вопрос 2. Введите: \"1,2,3\"", "1,2,3", QuestionType.MULTIPLY),
				new Question(3, "Вопрос 3. Введите следующий текст: \"текст правильного ответа\"", "текст правильного ответа", QuestionType.TEXT),
				new Question(4, "Вопрос 4. Введите: \"3\"", "3", QuestionType.SINGLE));
	}
}
