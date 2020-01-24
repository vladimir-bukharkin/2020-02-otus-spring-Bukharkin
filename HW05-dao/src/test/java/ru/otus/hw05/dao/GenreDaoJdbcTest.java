package ru.otus.hw05.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw05.domain.Genre;
import ru.otus.hw05.domain.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Тест genre DAO")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Sql({"/test-schema.sql", "/test-data.sql"})
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @Test
    public void testInsert() {
        List<Genre> expected = new ArrayList<>(getDefaultGenresInBD());
        Genre genre1 = new Genre("testName1");
        genreDaoJdbc.insert(genre1);
        genre1.setId(5L);
        expected.add(genre1);
        Assertions.assertThat(genreDaoJdbc.getAll()).isEqualTo(expected);

        Genre genre2 = new Genre(2L, "testName2");
        genreDaoJdbc.insert(genre2);
        genre2.setId(6L);
        expected.add(genre2);
        Assertions.assertThat(genreDaoJdbc.getAll()).isEqualTo(expected);
    }

    @Test
    public void testInsertFailBecauseAlreadyExists() {
        genreDaoJdbc.insert(new Genre("testName1"));
        Assertions.assertThatThrownBy(() -> genreDaoJdbc.insert(new Genre("testName1")))
                .isExactlyInstanceOf(DuplicateKeyException.class);
    }

    @Test
    public void testGetById() {
        getDefaultGenresInBD().forEach(expected -> {
            Assertions.assertThat(genreDaoJdbc.getById(expected.getId()))
                    .isEqualTo(expected);
        });
    }

    @Test
    public void testGetByIdForNotExistingGenre() {
        Assertions.assertThat(genreDaoJdbc.getById(899)).isNull();
    }

    @Test
    public void testGetByName() {
        getDefaultGenresInBD().forEach(expected -> {
            Assertions.assertThat(genreDaoJdbc.getByName(expected.getName()))
                    .isEqualTo(expected);
        });
    }

    @Test
    public void testGetByNameForNotExistingGenre() {
        Assertions.assertThat(genreDaoJdbc.getByName("some")).isNull();
    }

    @Test
    public void testGetAll() {
        List<Genre> expectedGenres = getDefaultGenresInBD();
        Assertions.assertThat(genreDaoJdbc.getAll()).isEqualTo(expectedGenres);
    }

    @Test
    public void testRemove() {
        List<Genre> expected = new ArrayList<>(getDefaultGenresInBD());
        genreDaoJdbc.remove(4);
        expected.remove(3);
        Assertions.assertThat(genreDaoJdbc.getAll()).isEqualTo(expected);
    }

    @Test
    public void testRemoveNotExistingGenre() {
        genreDaoJdbc.remove(222);
    }

    @Test
    public void testFailRemoveGenreBecauseForeignDependency() {
        Assertions.assertThatThrownBy(() -> genreDaoJdbc.remove(2)).isExactlyInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void testUpdate() {
        Genre updatedGenre = new Genre("newName");
        genreDaoJdbc.update(2, updatedGenre);
        updatedGenre.setId(2L);
        Assertions.assertThat(genreDaoJdbc.getById(2)).isEqualTo(updatedGenre);

        Genre updatedGenre2 = new Genre(6L, "novel");
        genreDaoJdbc.update(1, updatedGenre2);
        updatedGenre2.setId(1L);
        Assertions.assertThat(genreDaoJdbc.getById(1)).isEqualTo(updatedGenre2);
    }

    @Test
    public void testUpdateFailBecauseAlreadyExists() {
        genreDaoJdbc.insert(new Genre("testName"));
        Assertions.assertThatThrownBy(() -> genreDaoJdbc.update(1, new Genre("testName")))
                .isExactlyInstanceOf(DuplicateKeyException.class);
    }

    private List<Genre> getDefaultGenresInBD() {
        return Arrays.asList(new Genre(1L, "novel"),
                new Genre(2L, "story"),
                new Genre(3L, "education"),
                new Genre(4L, "other")
        );
    }
}
