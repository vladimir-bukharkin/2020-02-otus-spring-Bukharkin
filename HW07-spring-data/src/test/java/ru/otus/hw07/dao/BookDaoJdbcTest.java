package ru.otus.hw07.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw07.domain.Author;
import ru.otus.hw07.domain.Book;
import ru.otus.hw07.domain.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@DisplayName("Тест book DAO")
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Sql({"/test-schema.sql", "/test-data.sql"})
public class BookDaoJdbcTest {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookRepository bookRepository;


    @Test
    public void testInsert() {
        System.out.println(bookRepository.findAll());
    }

//    private List<Book> getDefaultBooksInBD() {
//        return Arrays.asList(
//                new Book(1L, "Plague", genres.get(0), Collections.singletonList(authors.get(2))),
//                new Book(2L, "Effective Java", genres.get(1), Collections.singletonList(authors.get(2))),
//                new Book(3L, "War and Peace", genres.get(0), Arrays.asList(authors.get(0), authors.get(1))),
//                new Book(4L, "Java Concurrency in Practice", genres.get(1), Collections.singletonList(authors.get(1))),
//                new Book(5L, "The Reactive Spring Book", genres.get(1), Collections.singletonList(authors.get(2)))
//        );
//    }
}
