package ru.otus.hw03;

import ru.otus.hw03.service.i18n.LocalizationService;

public class LocalizationServiceTestBean implements LocalizationService {

    private final String questionPath;

    public LocalizationServiceTestBean(String questionPath) {
        this.questionPath = questionPath;
    }

    @Override
    public String getMessage(String value) {
        return value;
    }

    @Override
    public String getMessage(String value, Object... objs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getQuestionsPath() {
        return questionPath;
    }
}
