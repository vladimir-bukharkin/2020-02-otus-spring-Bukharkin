package ru.otus.hw07.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw07.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author getByFirstNameAndLastName(String firstName, String lastName);
}
