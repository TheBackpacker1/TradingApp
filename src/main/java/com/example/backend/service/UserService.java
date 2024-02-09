package com.example.backend.service;


import com.example.backend.model.CoinDTO;
import com.example.backend.model.UserDTO;
import com.example.backend.persistence.Coin;
import com.example.backend.persistence.User;
import com.example.backend.persistence.UserRepository;
import com.example.backend.persistence.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
   user.setStatus(UserStatus.valueOf(userDTO.getStatus().toUpperCase()));
   userRepository.save(user);
     }


}
