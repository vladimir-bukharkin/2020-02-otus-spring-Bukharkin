package ru.otus.hw12.dao.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @NonNull
    private String name;
}
