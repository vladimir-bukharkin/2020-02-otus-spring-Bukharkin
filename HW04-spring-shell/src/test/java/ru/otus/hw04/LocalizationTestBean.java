package ru.otus.hw04;

import ru.otus.hw04.service.i18n.Localization;

public class LocalizationTestBean implements Localization {

    private final String questionPath;

    public LocalizationTestBean(String questionPath) {
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
