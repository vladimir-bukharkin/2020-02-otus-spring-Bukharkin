package ru.otus.hw02.service.i18n;

import ru.otus.hw02.config.LocalizationConfig;

public interface Localization {

    String getMessage(String value);

    String getMessage(String value, Object... objs);

    LocalizationConfig getConfig();
}
