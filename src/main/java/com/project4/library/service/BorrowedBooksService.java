package com.project4.library.service;



import com.project4.library.model.BorrowedBooks;
import com.project4.library.repository.BorrowedBooksRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BorrowedBooksService {

    @Autowired
    BorrowedBooksRepository borrowedBooksRepository;

    @Cacheable(value = "allBorrowedBooks", key = "#id")
    public List<BorrowedBooks> getAllBorrowedBooks(long id){
        return borrowedBooksRepository.findAll();
    }

}
