package com.project4.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "library_books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Enter the book title")
    private String title;

    @NotEmpty(message = "Enter the book author")
    private String author;

    @Column(unique = true)
    @Pattern(regexp = "[A-Z, a-z]{3}-[0-9]{3}", message = "Enter the book ISBN " +
            "with the naming pattern(###(alphabet)-###(number))")
    private String isbn;

    @Min(value = 4)
    private int year;

    @NotNull(message = "Enter the number of copy available")
    private int noOfCopyAvailable;

    public Books(String title, String author, String isbn, int year, int noOfCopyAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        this.noOfCopyAvailable = noOfCopyAvailable;
    }
}
