package ru.otus.hw03.service.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw03.config.ApplicationConfig;

import java.util.Locale;

@Service
public class DefaultLocalization implements Localization {

    private final MessageSource messageSource;
    private final String questionsPath;
    private final Locale locale;

    public DefaultLocalization(ApplicationConfig config, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.questionsPath = String.format(config.getQuestions().getPath(), config.getLocale().getLanguage());
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

    @Override
    public String getQuestionsPath() {
        return questionsPath;
    }
}