package com.project4.library.repository;

import com.project4.library.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findUserByFullName(String fullName);
    List<Users> findUserByEmail(String email);

    @Query(value = "SELECT * FROM library_users  where id = :id", nativeQuery = true)
    Users findByUserId(Long id);
}
