package ru.otus.hw07.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "Book",
        attributeNodes =
                {
                    @NamedAttributeNode("genre"),
                    @NamedAttributeNode("author")
                }
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @NonNull
    private String name;
    @NonNull
    @ManyToOne
    private Genre genre;
    @NonNull
    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    private List<Author> author;
}
