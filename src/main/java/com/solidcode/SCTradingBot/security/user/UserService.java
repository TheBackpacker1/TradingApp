package com.solidcode.SCTradingBot.security.user;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.solidcode.SCTradingBot.security.user.User user = getUser(username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ALL"));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    public com.solidcode.SCTradingBot.security.user.User getUser(String userName) {
        com.solidcode.SCTradingBot.security.user.User user = userRepo.findUserByUsername(userName);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User not Found.");
        }
        return user;
    }


    public com.solidcode.SCTradingBot.security.user.User createUser(String username, com.solidcode.SCTradingBot.security.user.User user) {
        user.setUsername(username);
        user.setPassword(user.getPassword());
        user.setCompleteName(user.getCompleteName());
        user.setStatus(user.getStatus());
       user.setRoles(user.getRoles());
        return userRepo.save(user);
    }

    public com.solidcode.SCTradingBot.security.user.User updateUserByUsername(String username, com.solidcode.SCTradingBot.security.user.User user) {
        // Retrieve the existing user from the database
        com.solidcode.SCTradingBot.security.user.User existingUser = userRepo.findUserByUsername(username);

        // Update the user properties
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setCompleteName(user.getCompleteName());
        existingUser.setStatus(user.getStatus());
        existingUser.setRoles(user.getRoles());

        // Save the updated user to the database
        return userRepo.save(existingUser);
    }

    public void deleteUserByUsername(@PathVariable String username) {
        com.solidcode.SCTradingBot.security.user.User user = userRepo.findUserByUsername(username);
        if (user != null) {
            userRepo.delete(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}

