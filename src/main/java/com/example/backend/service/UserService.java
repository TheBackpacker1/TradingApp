package com.example.backend.service;


import com.example.backend.model.CoinDTO;
import com.example.backend.model.UserDTO;
import com.example.backend.persistence.Coin;
import com.example.backend.persistence.User;
import com.example.backend.persistence.UserRepository;
import com.example.backend.persistence.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {



    private UserRepository userRepository ;

    @Autowired
    public UserService(UserRepository userRepository) {

       this.userRepository=userRepository ;
   }

    public List<User> getAllUsers() {

        return userRepository.findAll() ;
    }

    public Optional<User>getUserById(Integer user_id){

        return  userRepository.findById(user_id) ;

    }

    public void addUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setComplete_name(userDTO.getComplete_name());
        user.setStatus(userDTO.getStatus());
        userRepository.save(user);
    }


public User updateUser(Integer user_id, User updatedUser) {

    Optional<User> userOptional = userRepository.findById(user_id);

    if (userOptional.isPresent()) {
        User user = userOptional.get();
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setComplete_name(updatedUser.getComplete_name());
        user.setStatus(updatedUser.getStatus());
        userRepository.save(user);
        return user;
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
}

    public ResponseEntity<String> deleteUserById(Optional<Integer> user_id) {
        if (user_id.isPresent()) {
            Optional<User> optionalUser = userRepository.findById(user_id.get());
            if (optionalUser.isPresent()) {
                User existingUser = optionalUser.get();
                userRepository.delete(existingUser);
                return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found with id: " + user_id.get(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("User id is required", HttpStatus.BAD_REQUEST);
        }
    }

}
