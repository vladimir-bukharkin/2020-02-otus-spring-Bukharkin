package ru.otus.hw05.dao;

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
        genre1.setId(4);
        expected.add(genre1);
        Assertions.assertThat(genreDaoJdbc.getAll()).isEqualTo(expected);

        Genre genre2 = new Genre(2, "testName2");
        genreDaoJdbc.insert(genre2);
        genre2.setId(5);
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
    public void testGetByName() {
        getDefaultGenresInBD().forEach(expected -> {
            Assertions.assertThat(genreDaoJdbc.getByName(expected.getName()))
                    .isEqualTo(expected);
        });
    }

    @Test
    public void testGetAll() {
        List<Genre> expectedGenres = getDefaultGenresInBD();
        Assertions.assertThat(genreDaoJdbc.getAll()).isEqualTo(expectedGenres);
    }

    @Test
    public void testRemove() {
        List<Genre> expected = new ArrayList<>(getDefaultGenresInBD());
        genreDaoJdbc.remove(2);
        expected.remove(1);
        Assertions.assertThat(genreDaoJdbc.getAll()).isEqualTo(expected);

        genreDaoJdbc.remove(3);
        expected.remove(1);
        Assertions.assertThat(genreDaoJdbc.getAll()).isEqualTo(expected);
    }

    @Test
    public void testUpdate() {
        Genre updatedGenre = new Genre("newName");
        genreDaoJdbc.update(2, updatedGenre);
        updatedGenre.setId(2);
        Assertions.assertThat(genreDaoJdbc.getById(2)).isEqualTo(updatedGenre);

        Genre updatedGenre2 = new Genre(6, "novel");
        genreDaoJdbc.update(1, updatedGenre2);
        updatedGenre2.setId(1);
        Assertions.assertThat(genreDaoJdbc.getById(1)).isEqualTo(updatedGenre2);
    }


    @Test
    public void testUpdateFailBecauseAlreadyExists() {
        genreDaoJdbc.insert(new Genre("testName"));
        Assertions.assertThatThrownBy(() -> genreDaoJdbc.update(1, new Genre("testName")))
                .isExactlyInstanceOf(DuplicateKeyException.class);
    }

    private List<Genre> getDefaultGenresInBD() {
        return Arrays.asList(new Genre(1, "novel"),
                new Genre(2, "story"),
                new Genre(3, "education")
        );
    }
}
