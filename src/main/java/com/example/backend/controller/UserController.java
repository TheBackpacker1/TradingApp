package com.example.backend.controller;


import com.example.backend.model.UserDTO;
import com.example.backend.persistence.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {



    @Autowired
    private UserService userService ;


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> users=userService.getAllUsers() ;
        return  ResponseEntity.ok(users) ;

    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer user_id) {
        return userService.getUserById(user_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }






}
