package ru.otus.hw01.domain.question;

public enum QuestionType {
    SINGLE,
    MULTIPLY,
    TEXT;

    public static QuestionType instanceOf(String value) {
        String ucValue = value.toUpperCase();
        for (QuestionType c : QuestionType.values()) {
            if (c.name().equals(ucValue)) {
                return c;
            }
        }
        throw new RuntimeException(); //todo change it
    }
}
