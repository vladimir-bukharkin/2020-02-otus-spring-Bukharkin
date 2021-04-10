package ru.otus.hw11.dao.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;
}
