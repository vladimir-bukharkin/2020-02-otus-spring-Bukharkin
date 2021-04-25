package ru.otus.hw13.dto;

import lombok.*;

@Data
public class Comment {

    private String id;
    @NonNull
    private final String text;
    @NonNull
    private final Book book;
}
