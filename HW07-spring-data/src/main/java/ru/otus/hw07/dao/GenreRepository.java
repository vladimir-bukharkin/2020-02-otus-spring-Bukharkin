package ru.otus.hw07.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw07.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Genre getByName(String name);
}
