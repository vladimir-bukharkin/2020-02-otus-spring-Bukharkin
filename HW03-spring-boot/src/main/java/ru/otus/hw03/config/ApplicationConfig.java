package ru.otus.hw03.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("application")
public class ApplicationConfig {

    private Locale locale;
    private Question questions;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Question getQuestions() {
        return questions;
    }

    public void setQuestions(Question questions) {
        this.questions = questions;
    }

    public static class Locale {
        private String language;
        private String basenames;
        private String encodingDefault;

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getBasenames() {
            return basenames;
        }

        public void setBasenames(String basenames) {
            this.basenames = basenames;
        }

        public String getEncodingDefault() {
            return encodingDefault;
        }

        public void setEncodingDefault(String encodingDefault) {
            this.encodingDefault = encodingDefault;
        }
    }

    public static class Question {
        private String path;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames(locale.getBasenames());
        source.setDefaultEncoding(locale.getEncodingDefault());
        return source;
    }
}
