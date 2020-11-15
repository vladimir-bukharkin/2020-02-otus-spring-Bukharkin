package ru.otus.hw07.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
}
