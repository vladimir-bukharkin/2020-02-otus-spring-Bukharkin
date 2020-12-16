package ru.otus.hw08.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw08.dto.Author;
import ru.otus.hw08.dto.Book;
import ru.otus.hw08.dto.Comment;
import ru.otus.hw08.dto.Genre;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@DisplayName("Тест commentServiceImpl")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CommentServiceImplTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private CommentService commentService;

    private List<Book> books;

    @BeforeEach
    void setUp() {
        books = initBooks();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testInsert() {
        Comment expected = new Comment("text", books.get(1));
        Comment actual = commentService.save(expected);
        actual.setId(null);
        Assertions.assertThat(expected).isEqualTo(actual);

        expected = new Comment("text2", books.get(0));
        actual = commentService.save(expected);
        actual.setId(null);
        Assertions.assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindAll() {
        Comment expected1 = commentService.save(new Comment("text", books.get(1)));
        Comment expected2 = commentService.save(new Comment("text2", books.get(0)));
        Comment expected3 = commentService.save(new Comment("text3", books.get(1)));
        Assertions.assertThat(commentService.findAll()).isEqualTo(Arrays.asList(expected1, expected2, expected3));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testFindById() {
        Comment expected1 = commentService.save(new Comment("text", books.get(1)));
        Comment expected2 = commentService.save(new Comment("text2", books.get(0)));
        Comment expected3 = commentService.save(new Comment("text3", books.get(1)));
        Assertions.assertThat(commentService.findById(expected2.getId()).get()).isEqualTo(expected2);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDeleteBook() {
        Comment expected1 = commentService.save(new Comment("text", books.get(1)));
        Comment expected2 = commentService.save(new Comment("text2", books.get(0)));
        Comment expected3 = commentService.save(new Comment("text3", books.get(1)));
        commentService.delete(expected2);
        Assertions.assertThat(commentService.findAll()).isEqualTo(Arrays.asList(expected1, expected3));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDeleteById() {
        Comment expected1 = commentService.save(new Comment("text", books.get(1)));
        Comment expected2 = commentService.save(new Comment("text2", books.get(0)));
        Comment expected3 = commentService.save(new Comment("text3", books.get(1)));
        commentService.deleteById(expected2.getId());
        Assertions.assertThat(commentService.findAll()).isEqualTo(Arrays.asList(expected1, expected3));
    }

    private List<Book> initBooks() {
        Book expected1 = bookService.save(new Book("bookName", new Genre("genre1"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected2 = bookService.save(new Book("bookName2", new Genre("genre2"), Collections.singletonList(new Author("firstName", "lastName"))));
        Book expected3 = bookService.save(new Book("bookName3", new Genre("genre3"), Arrays.asList(new Author("firstName", "lastName"), new Author("f1", "f2"))));
        return Arrays.asList(expected1, expected2, expected3);
    }
}
