package ru.otus.hw10.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.hw10.dto.Book;
import ru.otus.hw10.exception.BookNotFoundException;
import ru.otus.hw10.service.BookService;


@RestController
public class BookController {

    private final BookService bookService;
    private final BookModelAssembler assembler;

    public BookController(BookService bookService, BookModelAssembler assembler) {
        this.bookService = bookService;
        this.assembler = assembler;
    }

    @GetMapping("/book")
    public CollectionModel<EntityModel<Book>> all() {
        return assembler.toCollectionModel(bookService.findAll());
    }

    @PostMapping("/book")
    public ResponseEntity<?> create(@RequestBody Book book) {
        var createdBook = assembler.toModel(bookService.save(book));

        return ResponseEntity.created(createdBook.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(createdBook);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        bookService.deleteById(id);

        return ResponseEntity.noContent()
                .build();
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<?> update(@RequestBody Book book, @PathVariable String id) {
        book.setId(id);
        var updatedBook = assembler.toModel(bookService.save(book));

        return ResponseEntity.created(updatedBook.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(updatedBook);
    }

    @GetMapping("/book/{id}")
    public EntityModel<Book> get(@PathVariable String id) throws BookNotFoundException {
        Book result = bookService.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return assembler.toModel(result);
    }

    @GetMapping("/edit_book")
    public String getBook(@RequestParam String id,
                          Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "editBook";
    }
}
