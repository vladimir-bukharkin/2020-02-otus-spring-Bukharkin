package ru.otus.hw06.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw06.dao.*;
import ru.otus.hw06.domain.Author;
import ru.otus.hw06.domain.Book;
import ru.otus.hw06.domain.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@DisplayName("Тест book DAO")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Sql({"/test-schema.sql", "/test-data.sql"})
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
public class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private AuthorDao authorDao;

    private List<Author> authors;
    private List<Genre> genres;

    @BeforeEach
    void setUp() {
        authors = authorDao.getAll();
        genres = genreDao.getAll();
    }

    @Test
    public void testInsert() {
        List<Book> expectedBooks = new ArrayList<>(getDefaultBooksInBD());
        Book book1 = new Book("test_book", genres.get(0), Arrays.asList(authors.get(0), authors.get(2)));
        bookDaoJdbc.insert(book1);
        book1.setId(6L);

        expectedBooks.add(book1);
        Assertions.assertThat(bookDaoJdbc.getAll()).isEqualTo(expectedBooks);

        Book book2 = new Book(4L, "test_book2", genres.get(1), Arrays.asList(authors.get(2), authors.get(1)));
        bookDaoJdbc.insert(book2);
        book2.setId(7L);
        expectedBooks.add(book2);
        Assertions.assertThat(bookDaoJdbc.getAll()).isEqualTo(expectedBooks);
    }

    @Test
    public void testGetById() {
        getDefaultBooksInBD().forEach(expected -> {
            Assertions.assertThat(bookDaoJdbc.getById(expected.getId()))
                    .isEqualTo(expected);
        });
    }

    @Test
    public void testGetByIdForNotExistingBook() {
        Assertions.assertThat(bookDaoJdbc.getById(899)).isNull();
    }

    @Test
    public void testGetByName() {
        getDefaultBooksInBD().forEach(expected -> {
            Assertions.assertThat(bookDaoJdbc.getByName(expected.getName()))
                    .isEqualTo(expected);
        });
    }

    @Test
    public void testGetByNameForNotExistingBook() {
        Assertions.assertThat(bookDaoJdbc.getByName("some")).isNull();
    }

    @Test
    public void testGetByAuthor() {
        getDefaultBooksInBD().forEach(expected -> {
            for (Author author : expected.getAuthors()) {
                List<Book> books = bookDaoJdbc.getByAuthor(author.getId());
                Assertions.assertThat(books).contains(expected);
            }
        });
    }

    @Test
    public void testGetByAuthorForNotExistingAuthor() {
        Assertions.assertThat(bookDaoJdbc.getByAuthor(879)).isEmpty();
    }

    @Test
    public void testGetByGenre() {
        getDefaultBooksInBD().forEach(expected -> {
            List<Book> books = bookDaoJdbc.getByGenre(expected.getGenre().getId());
            Assertions.assertThat(books).contains(expected);
        });
    }

    @Test
    public void testGetByGenreForNotExistingGenre() {
        Assertions.assertThat(bookDaoJdbc.getByGenre(879)).isEmpty();
    }

    @Test
    public void testGetByGenreForGenreWithoutBooks() {
        Assertions.assertThat(bookDaoJdbc.getByGenre(4)).isEmpty();
    }

    @Test
    public void testGetAll() {
        List<Book> expectedAuthors = getDefaultBooksInBD();
        Assertions.assertThat(bookDaoJdbc.getAll()).isEqualTo(expectedAuthors);
    }

    @Test
    public void testRemove() {
        List<Book> expected = new ArrayList<>(getDefaultBooksInBD());
        bookDaoJdbc.remove(2);
        expected.remove(1);
        Assertions.assertThat(bookDaoJdbc.getAll()).isEqualTo(expected);

        bookDaoJdbc.remove(3);
        expected.remove(1);
        Assertions.assertThat(bookDaoJdbc.getAll()).isEqualTo(expected);
    }

    @Test
    public void testUpdate() {
        Book book1 = new Book("test_book", genres.get(0), Arrays.asList(authors.get(2), authors.get(0)));
        bookDaoJdbc.update(2, book1);
        book1.setId(2L);
        Assertions.assertThat(bookDaoJdbc.getById(2)).isEqualTo(book1);

        Book book2 = new Book(6L, "test_book2", genres.get(1), Arrays.asList(authors.get(2), authors.get(1)));
        bookDaoJdbc.update(1, book2);
        book2.setId(1L);
        Assertions.assertThat(bookDaoJdbc.getById(1)).isEqualTo(book2);
    }

    @Test
    public void testInsertFailBecauseAuthorBookAlreadyExists() {
        Book book1 = new Book("test_book", genres.get(0), Arrays.asList(authors.get(2), authors.get(2)));
        Assertions.assertThatThrownBy(() -> bookDaoJdbc.insert(book1)).isExactlyInstanceOf(DuplicateKeyException.class);
    }

    private List<Book> getDefaultBooksInBD() {
        return Arrays.asList(
                new Book(1L, "Plague", genres.get(0), Collections.singletonList(authors.get(2))),
                new Book(2L, "Effective Java", genres.get(1), Collections.singletonList(authors.get(2))),
                new Book(3L, "War and Peace", genres.get(0), Arrays.asList(authors.get(0), authors.get(1))),
                new Book(4L, "Java Concurrency in Practice", genres.get(1), Collections.singletonList(authors.get(1))),
                new Book(5L, "The Reactive Spring Book", genres.get(1), Collections.singletonList(authors.get(2)))
        );
    }
}
