package ru.otus.hw05.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Author {

    @Setter
    private Long id;
    private final String firstName;
    private final String lastName;
}
