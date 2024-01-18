package com.project4.library.service;

import com.project4.library.model.Users;
import com.project4.library.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Cacheable(value = "getAllUsers")
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @CacheEvict(value = {"getAllUsers", "allUsers"}, allEntries = true)
    public Users addUser(Users users){
        return userRepository.save(users);
    }

    @CacheEvict(value = {"singleUser", "id"}, allEntries = true)
    public ResponseEntity<String> deleteUser(long id){
        userRepository.deleteById(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @CacheEvict(value = {"singleUser", "allUsers"}, allEntries = true)
    public ResponseEntity<String> updateUser (Long id, Users users){
        Users update = findUserById(id);

        update.setFullName(users.getFullName());
        update.setEmail(users.getEmail());
        update.setAge(users.getAge());
        update.setAddress(users.getAddress());

        userRepository.save(update);
        return new ResponseEntity<>("User with the id has been" +
                " updated successfully", HttpStatus.OK);
    }

    public Users findUserById(long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<Users> findUserByFullName(String fullName){
        return userRepository.findUserByFullName(fullName);
    }

    public List<Users> findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

}
