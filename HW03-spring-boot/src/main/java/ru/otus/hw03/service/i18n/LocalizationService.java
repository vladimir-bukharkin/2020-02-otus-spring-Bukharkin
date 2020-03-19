package ru.otus.hw03.service.i18n;

public interface LocalizationService {

    String getMessage(String value);

    String getMessage(String value, Object... objs);

    String getQuestionsPath();
}
