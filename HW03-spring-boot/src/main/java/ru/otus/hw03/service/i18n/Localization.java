package ru.otus.hw03.service.i18n;

import ru.otus.hw03.config.LocalizationConfig;

public interface Localization {

    String getMessage(String value);

    String getMessage(String value, Object... objs);

    LocalizationConfig getConfig();
}
