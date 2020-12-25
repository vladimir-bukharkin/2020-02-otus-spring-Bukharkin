package ru.otus.hw09.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.otus.hw09.dto.Author;
import ru.otus.hw09.dto.Book;
import ru.otus.hw09.dto.Comment;
import ru.otus.hw09.dto.Genre;

@Mapper(componentModel = "spring")
public interface LibraryMapper {

    LibraryMapper INSTANCE = Mappers.getMapper( LibraryMapper.class );

    Genre map(ru.otus.hw09.dao.domain.Genre genre);

    ru.otus.hw09.dao.domain.Genre map(Genre genre);

    ru.otus.hw09.dao.domain.Author map(Author author);

    Author map(ru.otus.hw09.dao.domain.Author author);

    Book map(ru.otus.hw09.dao.domain.Book book);

    ru.otus.hw09.dao.domain.Book map(Book book);

    ru.otus.hw09.dao.domain.Comment map(Comment comment);

    Comment map(ru.otus.hw09.dao.domain.Comment comment);
}
