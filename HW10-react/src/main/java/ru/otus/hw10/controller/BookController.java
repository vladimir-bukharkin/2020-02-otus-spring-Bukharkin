package ru.otus.hw10.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw10.dto.Book;
import ru.otus.hw10.exception.DomainObjectNotFoundException;
import ru.otus.hw10.service.BookService;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public List<Book> all() {
        return bookService.findAll();
    }

    @GetMapping("/book/{id}")
    public Book get(@PathVariable String id) throws DomainObjectNotFoundException {
        return bookService.findById(id).orElseThrow(() -> new DomainObjectNotFoundException("Book", id));
    }

    @GetMapping("/edit_book")
    public String getBook(@RequestParam String id,
                          Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "editBook";
    }
}
