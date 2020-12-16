package ru.otus.hw08.dao.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private Genre genre;

    @NonNull
    @Field(name = "authors")
    private List<Author> authors;
}
