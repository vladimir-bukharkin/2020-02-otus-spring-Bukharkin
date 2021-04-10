package ru.otus.hw11.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw11.dto.Author;
import ru.otus.hw11.dto.Book;
import ru.otus.hw11.dto.Comment;
import ru.otus.hw11.dto.Genre;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Predicate;

@DisplayName("bookServiceImpl")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private CommentService commentService;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testInsert() {
        Book expected = new Book("bookName", new Genre("genre1"), Collections.singletonList(new Author("firstName", "lastName")));
        Book actual = bookService.save(expected);
        assertBook(expected, actual);

        expected = new Book("bookName2", new Genre("genre2"), Collections.singletonList(new Author("firstName2", "lastName2")));
        actual = bookService.save(expected);
        assertBook(expected, actual);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindAll() {
        Book expected1 = bookService.save(new Book("bookName", new Genre("genre1"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected2 = bookService.save(new Book("bookName2", new Genre("genre2"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected3 = bookService.save(new Book("bookName3", new Genre("genre3"), Arrays.asList(new Author("firstName", "lastName"), new Author("f1", "f2"))));
        Assertions.assertThat(bookService.findAll()).isEqualTo(Arrays.asList(expected1, expected2, expected3));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindAllByGenre() {
        Genre genre = new Genre("genre2");
        Book expected1 = bookService.save(new Book("bookName", new Genre("genre1"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected2 = bookService.save(new Book("bookName2", genre, Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected3 = bookService.save(new Book("bookName3", genre, Arrays.asList(new Author("firstName", "lastName"), new Author("f1", "f2"))));
        Assertions.assertThat(bookService.findAllByGenre(genre)).isEqualTo(Arrays.asList(expected2, expected3));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindById() {
        Book expected1 = bookService.save(new Book("bookName", new Genre("genre1"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected2 = bookService.save(new Book("bookName2", new Genre("genre2"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected3 = bookService.save(new Book("bookName3", new Genre("genre3"), Arrays.asList(new Author("firstName", "lastName"), new Author("f1", "f2"))));
        Assertions.assertThat(bookService.findById(expected2.getId()).get()).isEqualTo(expected2);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindByAuthorContaining() {
        Book expected1 = bookService.save(new Book("bookName", new Genre("genre1"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected2 = bookService.save(new Book("bookName2", new Genre("genre2"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected3 = bookService.save(new Book("bookName3", new Genre("genre3"), Arrays.asList(new Author("firstName", "lastName"), new Author("f1", "f2"))));
        Assertions.assertThat(bookService.findAllByAuthorsContaining(expected3.getAuthors().get(1))).isEqualTo(Collections.singletonList(expected3));
        Assertions.assertThat(bookService.findAllByAuthorsContaining(expected3.getAuthors().get(0))).isEqualTo(Arrays.asList(expected1, expected2, expected3));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDeleteBook() {
        Book expected1 = bookService.save(new Book("bookName", new Genre("genre1"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected2 = bookService.save(new Book("bookName2", new Genre("genre2"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected3 = bookService.save(new Book("bookName3", new Genre("genre3"), Arrays.asList(new Author("firstName", "lastName"), new Author("f1", "f2"))));

        commentService.save(new Comment("comment1", expected2));
        commentService.save(new Comment("comment2", expected2));
        Comment comment = commentService.save(new Comment("comment2", expected3));

        bookService.delete(expected2);
        Assertions.assertThat(bookService.findAll()).isEqualTo(Arrays.asList(expected1, expected3));

        Assertions.assertThat(commentService.findAll()).isEqualTo(Collections.singletonList(comment));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDeleteBookById() {
        Book expected1 = bookService.save(new Book("bookName", new Genre("genre1"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected2 = bookService.save(new Book("bookName2", new Genre("genre2"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected3 = bookService.save(new Book("bookName3", new Genre("genre3"), Arrays.asList(new Author("firstName", "lastName"), new Author("f1", "f2"))));
        bookService.deleteById(expected2.getId());
        Assertions.assertThat(bookService.findAll()).isEqualTo(Arrays.asList(expected1, expected3));
    }

    private void assertBook(Book expected, Book actual) {
        Assertions.assertThat(expected).matches(bookEquality(actual), "Actual is: " + actual);
    }

    private static Predicate<Book> bookEquality(Book b) {
        return book -> {
            if (book == b) return true;
            if (b == null || book.getClass() != b.getClass()) return false;
            return Objects.equals(b.getName(), book.getName()) &&
                    Objects.equals(b.getGenre(), book.getGenre()) &&
                    Objects.equals(b.getAuthors(), book.getAuthors());
        };
    }
}
