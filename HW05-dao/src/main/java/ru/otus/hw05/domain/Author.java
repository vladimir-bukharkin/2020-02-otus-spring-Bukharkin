package ru.otus.hw05.domain;

import lombok.Data;

@Data
public class Author {

    private final long id;
    private final String firstName;
    private final String lastName;
}
