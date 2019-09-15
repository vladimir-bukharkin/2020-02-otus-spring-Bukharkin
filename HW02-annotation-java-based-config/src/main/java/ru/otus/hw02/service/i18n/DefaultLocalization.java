package ru.otus.hw02.service.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw02.config.LocalizationConfig;


@Service
public class DefaultLocalization implements Localization {

    private final MessageSource messageSource;
    private final LocalizationConfig config;

    public DefaultLocalization(LocalizationConfig config) {
        this.config = config;
        this.messageSource = initMessageSource();
    }

    @Override
    public String getMessage(String value) {
        return messageSource.getMessage(value, null, config.getLocale());
    }

    @Override
    public String getMessage(String value, Object... objs) {
        return messageSource.getMessage(value, objs, config.getLocale());
    }

    @Override
    public LocalizationConfig getConfig() {
        return config;
    }

    private MessageSource initMessageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames(config.getBasenames());
        source.setDefaultEncoding(config.getDefaultEncoding());
        return source;
    }
}