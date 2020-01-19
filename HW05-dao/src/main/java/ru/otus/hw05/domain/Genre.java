package ru.otus.hw05.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Genre {

    @Setter
    private long id;
    private final String name;
}
