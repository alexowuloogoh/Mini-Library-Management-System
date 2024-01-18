package com.project4.library.repository;

import com.project4.library.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    @Query(value = "select * from library_books where id = :id", nativeQuery = true)
    Books findByBookId(Long id);
    List<Books> findByTitle(String title);
    Books findByIsbn(String isbn);
}
