package ru.otus.hw06.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw06.domain.Author;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Тест author DAO")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Sql({"/test-schema.sql", "/test-data.sql"})
@Import(AuthorDaoJdbc.class)
public class AuthorDaoJdbcTest{

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    public void testInsert() {
        List<Author> expectedAuthors = new ArrayList<>(getDefaultAuthorsInBD());
        Author author1 = new Author("testFirstName1", "testLastName1");
        authorDaoJdbc.insert(author1);
        author1.setId(4L);
        expectedAuthors.add(author1);
        Assertions.assertThat(authorDaoJdbc.getAll()).isEqualTo(expectedAuthors);

        Author author2 = new Author(2L, "testFirstName2", "testLastName2");
        authorDaoJdbc.insert(author2);
        author2.setId(5L);
        expectedAuthors.add(author2);
        Assertions.assertThat(authorDaoJdbc.getAll()).isEqualTo(expectedAuthors);
    }

    @Test
    public void testInsertFailBecauseAlreadyExists() {
        authorDaoJdbc.insert(new Author("testFirstName1", "testLastName1"));
        Assertions.assertThatThrownBy(() -> authorDaoJdbc.insert(new Author("testFirstName1", "testLastName1")))
                .isExactlyInstanceOf(DuplicateKeyException.class);
    }

    @Test
    public void testGetById() {
        getDefaultAuthorsInBD().forEach(expected -> {
            Assertions.assertThat(authorDaoJdbc.getById(expected.getId()))
                    .isEqualTo(expected);
        });
    }

    @Test
    public void testGetByIdForNotExistingAuthor() {
        Assertions.assertThat(authorDaoJdbc.getById(899)).isNull();
    }

    @Test
    public void testGetByName() {
        getDefaultAuthorsInBD().forEach(expected -> {
            Assertions.assertThat(authorDaoJdbc.getByName(expected.getFirstName(), expected.getLastName()))
                    .isEqualTo(expected);
        });
    }

    @Test
    public void testGetByNameForNotExistingAuthor() {
        Assertions.assertThat(authorDaoJdbc.getByName("some", "name")).isNull();
    }

    @Test
    public void testGetAll() {
        List<Author> expectedAuthors = getDefaultAuthorsInBD();
        Assertions.assertThat(authorDaoJdbc.getAll()).isEqualTo(expectedAuthors);
    }

    @Test
    public void testRemove() {
        List<Author> expected = new ArrayList<>(getDefaultAuthorsInBD());
        authorDaoJdbc.remove(2);
        expected.remove(1);
        Assertions.assertThat(authorDaoJdbc.getAll()).isEqualTo(expected);

        authorDaoJdbc.remove(3);
        expected.remove(1);
        Assertions.assertThat(authorDaoJdbc.getAll()).isEqualTo(expected);
    }

    @Test
    public void testRemoveNotExistingAuthor() {
        authorDaoJdbc.remove(222);
    }

    @Test
    public void testUpdate() {
        Author updatedAuthor = new Author("newFirstName", "newLastName");
        authorDaoJdbc.update(2, updatedAuthor);
        updatedAuthor.setId(2L);
        Assertions.assertThat(authorDaoJdbc.getById(2)).isEqualTo(updatedAuthor);

        Author updatedAuthor2 = new Author(6L, "Lev", "Tolstoy");
        authorDaoJdbc.update(1, updatedAuthor2);
        updatedAuthor2.setId(1L);
        Assertions.assertThat(authorDaoJdbc.getById(1)).isEqualTo(updatedAuthor2);
    }

    @Test
    public void testUpdateFailBecauseAlreadyExists() {
        authorDaoJdbc.insert(new Author("testFirstName1", "testLastName1"));
        Assertions.assertThatThrownBy(() -> authorDaoJdbc.update(1, new Author("testFirstName1", "testLastName1")))
                .isExactlyInstanceOf(DuplicateKeyException.class);
    }

    private List<Author> getDefaultAuthorsInBD() {
        return Arrays.asList(new Author(1L,"Lev", "Tolstoy"),
                new Author(2L, "Joshua", "Bloch"),
                new Author(3L, "Josh", "Long")
        );
    }
}
