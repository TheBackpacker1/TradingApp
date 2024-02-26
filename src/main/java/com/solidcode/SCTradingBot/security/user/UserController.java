package com.solidcode.SCTradingBot.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = {"/{userName}"})
    public ResponseEntity<User> getUser(@PathVariable String userName) {

        try {
            return ResponseEntity.ok().body(userService.getUser(userName));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
    @PostMapping("/createUser")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<com.solidcode.SCTradingBot.security.user.User> createUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setUsername(user.getUsername());
        com.solidcode.SCTradingBot.security.user.User savedUser = userService.createUser(user.getUsername(), user);
        return ResponseEntity.ok().body(savedUser);
    }
   @PutMapping("/updateUser/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody com.solidcode.SCTradingBot.security.user.User user) {
        // Update the user in the database
        User updatedUser = userService.updateUserByUsername(username, user);

        // Return the updated user in the response
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        // Delete the user from the database
        userService.deleteUserByUsername(username);

        // Return an HTTP 200 OK status code
        return ResponseEntity.ok().build();
    }
}
