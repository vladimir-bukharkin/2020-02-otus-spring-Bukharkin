package ru.otus.hw03.service.i18n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw03.config.ApplicationConfig;

import java.util.Locale;

@Service
public class DefaultLocalizationService implements LocalizationService {

    private final MessageSource messageSource;
    private final Locale locale;

    public DefaultLocalizationService(ApplicationConfig config, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(config.getLocale().getLanguage());
    }

    @Override
    public String getMessage(String value) {
        return messageSource.getMessage(value, null, locale);
    }

    @Override
    public String getMessage(String value, Object... objs) {
        return messageSource.getMessage(value, objs, locale);
    }
}