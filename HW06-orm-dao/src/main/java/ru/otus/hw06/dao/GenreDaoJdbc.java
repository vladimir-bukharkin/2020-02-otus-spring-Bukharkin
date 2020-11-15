package ru.otus.hw06.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.hw06.domain.Genre;

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
                .addValue("Name", genre.getName());
        jdbc.update("INSERT INTO Genres (Name) values(:Name)", params);
    }

    @Override
    public Genre getById(long id) {
        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("Id", id);
            return jdbc.queryForObject("SELECT * FROM Genres WHERE Id=:Id", params, new GenreMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Genre getByName(String name) {
        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("Name", name);
            return jdbc.queryForObject("SELECT * FROM Genres WHERE Name=:Name",
                    params,
                    new GenreMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("SELECT * FROM Genres", new GenreMapper());
    }

    @Override
    public void update(long id, Genre genre) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("Id", id)
                .addValue("Name", genre.getName());
        jdbc.update("UPDATE Genres SET Name=:Name WHERE Id=:Id", params);
    }

    @Override
    public void remove(long id) {
        jdbc.update("DELETE FROM Genres WHERE Id=:Id", new MapSqlParameterSource()
                .addValue("Id", id));
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getLong("Id"),
                    rs.getString("Name"));
        }
    }
}
