package ru.otus.hw02.service.i18n;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@PropertySource("classpath:application.properties")
public class DefaultLocalization implements Localization {

    private final MessageSource messageSource;
    private final Locale locale;
    private final String questionsPath;

    public DefaultLocalization(@Value("${locale.language}") String locale,
                               MessageSource messageSource,
                               @Value("${questions.path}") String questionsPath) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(locale);
        this.questionsPath = String.format(questionsPath, locale);
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