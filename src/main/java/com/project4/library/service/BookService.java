package com.project4.library.service;

import com.project4.library.dto.BorrowedBookRequest;
import com.project4.library.exceptions.BookNotFoundException;
import com.project4.library.exceptions.UserNotFoundException;
import com.project4.library.model.Books;
import com.project4.library.model.BorrowedBooks;
import com.project4.library.model.Users;
import com.project4.library.repository.BookRepository;
import com.project4.library.repository.BorrowedBooksRepository;
import com.project4.library.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BorrowedBooksRepository borrowedBooksRepository;

    @Autowired
    UserRepository userRepository;

    @Cacheable(value = "allBooks")
    public List<Books> getAllBooks(Books books){
        return bookRepository.findAll();
    }

    @CacheEvict(value = "books", allEntries = true)
    public ResponseEntity<Books> addBook(Books books){
        return new ResponseEntity<>(bookRepository.save(books), HttpStatus.CREATED);
    }

    @CacheEvict(value = "books")
    public ResponseEntity<String> deleteBook(long id){
        bookRepository.deleteById(id);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
    }

    @CacheEvict(value = "borrowBook")
    public ResponseEntity<BorrowedBooks> borrowBook(BorrowedBookRequest borrowedBooks) throws UserNotFoundException, BookNotFoundException {
        Users user1 = userRepository.findByUserId(borrowedBooks.getUserId());
        Books book1 = bookRepository.findByBookId(borrowedBooks.getBookId());

        try{
            if (user1 == null);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }

        try{
            if (book1 == null);
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException(e.getMessage());
        }

        BorrowedBooks borrowedBooks1 = new BorrowedBooks();
        borrowedBooks1.setBooks(book1);
        borrowedBooks1.setUsers(user1);
        assert book1 != null;
        borrowedBooks1.setBookTitle(book1.getTitle());
        borrowedBooks1.setBookAuthor(book1.getAuthor());
        borrowedBooksRepository.save(borrowedBooks1);

        book1.setNoOfCopyAvailable(book1.getNoOfCopyAvailable()-1);
        bookRepository.save(book1);

        return new ResponseEntity<>(borrowedBooks1, HttpStatus.OK);
    }

/*    @CacheEvict(value = "returnBook")
    public ResponseEntity<String> returnBook(BorrowedBooks returnBook) {
        Optional<BorrowedBooks> book = borrowedBooksRepository.findById(returnBook.getId());
        Books books = bookRepository.findByBookId(returnBook.getId());

        book.ifPresent(borrowedBooksRepository::delete);
        borrowedBooksRepository.save(returnBook);

        books.setNoOfCopyAvailable(books.getNoOfCopyAvailable()+1);
        bookRepository.save(books);

        return new ResponseEntity<>("Book successfully returned", HttpStatus.ACCEPTED);
    }*/

    public Books findBookById(long id){
        return bookRepository.findByBookId(id);
    }

    public List<Books> findBookByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public Books findBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

}
