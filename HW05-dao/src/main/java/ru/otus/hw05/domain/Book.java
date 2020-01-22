package ru.otus.hw05.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {

    @Setter
    private Long id;
    private final String name;
    private final Genre genre;
    private final List<Author> authors;
}
