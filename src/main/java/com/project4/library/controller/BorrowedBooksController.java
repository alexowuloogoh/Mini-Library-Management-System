package com.project4.library.controller;

import com.project4.library.model.BorrowedBooks;
import com.project4.library.service.BorrowedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BorrowedBooksController {

    @Autowired
    BorrowedBooksService borrowedBooksService;

    @GetMapping("/borrowBook")
    public List<BorrowedBooks> getAllBorrowBooks(long id){
        return borrowedBooksService.getAllBorrowedBooks(id);
    }
}
