package ru.otus.hw05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.hw05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao{

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Genre genre) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", genre.getName());
        jdbc.update("INSERT INTO genres (name) values(:name)", params);
    }

    @Override
    public Genre getById(long id) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbc.queryForObject("SELECT * FROM genres WHERE id=:id", params, new GenreMapper());
    }

    @Override
    public Genre getByName(String name) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", name);
        return jdbc.queryForObject("SELECT * FROM genres WHERE name=:name",
                params,
                new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("SELECT * FROM genres", new GenreMapper());
    }

    @Override
    public void update(long id, Genre genre) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", genre.getName());
        jdbc.update("UPDATE genres SET name=:name WHERE id=:id", params);
    }

    @Override
    public void remove(long id) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        jdbc.update("DELETE FROM genres WHERE id=:id", params);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getInt("id"),
                    rs.getString("name"));
        }
    }
}