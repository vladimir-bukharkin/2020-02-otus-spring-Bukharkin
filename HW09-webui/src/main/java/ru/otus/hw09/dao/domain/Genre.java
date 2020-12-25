package ru.otus.hw09.dao.domain;

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
