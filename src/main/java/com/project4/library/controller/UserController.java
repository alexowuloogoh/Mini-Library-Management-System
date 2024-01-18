package com.project4.library.controller;

import com.project4.library.model.Users;
import com.project4.library.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Users> addUser(@RequestBody @Valid Users users){
        return new ResponseEntity<>(userService.addUser(users), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser (@Valid @PathVariable("id") long id, @RequestBody Users users){
        return userService.updateUser(id, users);
    }

    @GetMapping("users/id/{id}")
    @ResponseBody
    public Users findUserById(@PathVariable("id") long id){
        return userService.findUserById(id);
    }

    @GetMapping("users/name/{fullName}")
    @ResponseBody
    public List<Users> findUserByFullName(@PathVariable("fullName") String fullName){
        return userService.findUserByFullName(fullName);
    }

    @GetMapping("users/email/{email}")
    @ResponseBody
    public List<Users> findUserByEmail(@PathVariable("email") String email){
        return userService.findUserByEmail(email);
    }
}
