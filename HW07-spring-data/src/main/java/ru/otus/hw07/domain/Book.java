package ru.otus.hw07.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @NonNull
    private String name;
    @NonNull
    @OneToOne
    private Genre genre;
    @NonNull
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    private List<Author> author;
}
