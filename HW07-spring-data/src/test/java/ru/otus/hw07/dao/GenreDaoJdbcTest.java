package ru.otus.hw07.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw07.domain.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@DisplayName("Тест genre DAO")
@ExtendWith(SpringExtension.class)
@Sql({"/test-schema.sql", "/test-data.sql"})
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class GenreDaoJdbcTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void testInsert() {
        List<Genre> expected = new ArrayList<>(getDefaultGenresInBD());
        Genre genre1 = new Genre("testName1");
        genreRepository.save(genre1);
        genre1.setId(5);
        expected.add(genre1);
        Assertions.assertThat(genreRepository.findAll()).isEqualTo(expected);

        Genre genre2 = new Genre("testName2");
        genreRepository.save(genre2);
        genre2.setId(6);
        expected.add(genre2);
        Assertions.assertThat(genreRepository.findAll()).isEqualTo(expected);
    }

    private List<Genre> getDefaultGenresInBD() {
        return Arrays.asList(new Genre(1, "novel"),
                new Genre(2, "story"),
                new Genre(3, "education"),
                new Genre(4, "other")
        );
    }
}
