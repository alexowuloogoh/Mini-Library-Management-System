package com.project4.library.repository;

import com.project4.library.model.BorrowedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> {
}