package com.project4.library.controller;

import com.project4.library.dto.BorrowedBookRequest;
import com.project4.library.model.Books;
import com.project4.library.model.BorrowedBooks;
import com.project4.library.service.BookService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/api/v1")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Books>> getAllBooks(Books books){
        return new ResponseEntity<>(bookService.getAllBooks(books), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Books> addBook(@Valid @RequestBody Books books){
        return bookService.addBook(books);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable long id){
        return bookService.deleteBook(id);
    }

    @PostMapping("/books/borrowed")
    public ResponseEntity<BorrowedBooks> borrowBook(@RequestBody BorrowedBookRequest borrowedBooks) {
       return bookService.borrowBook(borrowedBooks);
    }


    @GetMapping("/books/{id}")
    @ResponseBody
    public Books findBookById(@PathVariable long id){
        return bookService.findBookById(id);
    }

    @GetMapping("/books/title/{title}")
    public List<Books> findBookByTitle(@PathVariable String title){
        return bookService.findBookByTitle(title);
    }

    @GetMapping("/books/isbn/{isbn}")
    public Books findBookByIsbn(@PathVariable String isbn){
        return bookService.findBookByIsbn(isbn);
    }

}
