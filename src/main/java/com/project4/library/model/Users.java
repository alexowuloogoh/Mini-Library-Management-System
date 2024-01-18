package com.project4.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "library_users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(name = "full_name")
    @Length(min = 6, max = 15, message = "Enter your full name")
    private String fullName;

    @Email(message = "Enter your email address")
    private String email;

    @Min(value = 18, message = "You must be up to 18 years of age")
    @Max(value = 70, message = "You must not have exceeded 70 years of age")
    private Integer age;

    @NotEmpty
    private String address;

    public Users(String fullName, String email, Integer age, String address) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.address = address;
    }
}