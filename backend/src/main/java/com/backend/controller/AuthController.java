package com.backend.controller;

import com.backend.user.User;
import com.backend.exception.UserAlreadyExistsException;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        try {
            User registeredUser = userService.register(user);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>("User with this email already exists.", HttpStatus.BAD_REQUEST);
        } catch (Throwable t) {
            // Handle other exceptions
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // Check if the user exists in the database
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser == null) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
        // Check if the password matches
        if (existingUser.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }
}
