package ru.otus.hw12.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class Book {

    private String id;
    @NonNull
    private final String name;
    @NonNull
    private final Genre genre;
    @NonNull
    private final List<Author> authors;
}
