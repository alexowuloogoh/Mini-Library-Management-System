package com.project4.library.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrowed_books")
public class BorrowedBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "books_title")
    private String bookTitle;

    @Column(name = "books_author")
    private String bookAuthor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "books_id")
    private Books books;

}