package com.project.lms.ExpenseTrackerLms.controller;

import com.project.lms.ExpenseTrackerLms.dto.UserDTO;
import com.project.lms.ExpenseTrackerLms.entity.User;
import com.project.lms.ExpenseTrackerLms.services.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    // Signup Endpoint: Register a new user
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) {
        try {
            // Register the user using the service
            User newUser = userService.registerUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully: " + newUser.getUsername());
        } catch (Exception e) {
            // Handle errors such as username already taken
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    // Login Endpoint: Authenticate the user
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserDTO request) {
//        boolean isAuthenticated = userService.authenticateUser(request.getUsername(), request.getPassword());
//        System.out.println("here");
//        if (isAuthenticated) {
//            // Return success message (In real apps, you would return a JWT token)
//            return ResponseEntity.status(HttpStatus.OK).body("Login successful");
//        } else {
//            // Return failure message if authentication fails
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//    }
    @PostMapping("/login")
    public ResponseEntity<Optional<User>> login(@RequestBody UserDTO request) {
        System.out.println("here"); // For debugging
        boolean isAuthenticated = userService.authenticateUser(request.getUsername(), request.getPassword());

        if (isAuthenticated) {
            Optional<User> user = userService.findUser(request.getUsername());
            // Return success message (In real apps, you would return a JWT token)
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            // Return failure message if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Optional.empty());
        }
    }

}
