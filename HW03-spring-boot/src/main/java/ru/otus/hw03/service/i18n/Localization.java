package ru.otus.hw03.service.i18n;

public interface Localization {

    String getMessage(String value);

    String getMessage(String value, Object... objs);

    String getQuestionsPath();
}
