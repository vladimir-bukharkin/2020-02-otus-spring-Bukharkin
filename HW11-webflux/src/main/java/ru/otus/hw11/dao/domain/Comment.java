package ru.otus.hw11.dao.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    private String id;

    @NonNull
    private String text;

    @NonNull
    @DBRef(lazy = true)
    private Book book;
}
