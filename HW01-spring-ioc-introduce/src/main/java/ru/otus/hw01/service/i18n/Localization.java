package ru.otus.hw01.service.i18n;

public interface Localization {

    String getMessage(String value);

    String getMessage(String value, Object... objs);

    String getQuestionsPath();
}
