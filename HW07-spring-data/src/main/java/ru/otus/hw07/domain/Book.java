package ru.otus.hw07.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    @OneToOne
    private Genre genre;
    @NonNull
    @OneToMany
    private List<Author> author;
}
