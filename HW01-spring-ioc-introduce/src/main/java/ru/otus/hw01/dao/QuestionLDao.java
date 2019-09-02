package ru.otus.hw01.dao;

import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.service.dataloader.QuestionDataLoader;

import java.util.List;

public class QuestionLDao implements QuestionDao {

    private final QuestionDataLoader dataLoader;

    public QuestionLDao(QuestionDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public List<Question> getAll() {
        return dataLoader.loadAllToList();
    }
}
