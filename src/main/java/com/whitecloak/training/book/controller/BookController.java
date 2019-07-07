package com.whitecloak.training.book.controller;

import com.whitecloak.training.book.request.BookForm;
import com.whitecloak.training.book.response.BookResource;
import com.whitecloak.training.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookResource create(@RequestBody BookForm bookForm) {
        return bookService.create(bookForm);
    }

    @GetMapping
    public List<BookResource> showAll() {
        return bookService.showAll();
    }

    @GetMapping("/{id}")
    public BookResource showOne(@PathVariable Long id) {
        return bookService.showOne(id);
    }
}
