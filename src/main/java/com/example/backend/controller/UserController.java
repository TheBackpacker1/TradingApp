package com.example.backend.controller;


import com.example.backend.model.UserDTO;
import com.example.backend.persistence.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/{user_id}")
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


@PutMapping("/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer user_id,@RequestBody User updatedUser) {

        User user = userService.updateUser(user_id,updatedUser) ;
        return new ResponseEntity<>(user,HttpStatus.OK);
}


    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Optional<Integer> user_id) {
        return userService.deleteUserById(user_id);
    }

}
