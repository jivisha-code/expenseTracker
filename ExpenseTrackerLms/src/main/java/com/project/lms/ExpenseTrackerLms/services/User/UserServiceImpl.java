package com.project.lms.ExpenseTrackerLms.services.User;

import com.project.lms.ExpenseTrackerLms.dto.UserDTO;
import com.project.lms.ExpenseTrackerLms.entity.User;
import com.project.lms.ExpenseTrackerLms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // SignUp Method - to register a new user
    @Override
    public User registerUser(UserDTO userDTO) {
        // Check if the username already exists
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists.");
        }

        // Create a new User entity
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));  // Encrypt the password
        newUser.setEmail(userDTO.getEmail());

        // Save the user to the database
        return userRepository.save(newUser);
    }

    // Login Method - authenticate the user by checking username and password
    @Override
    public boolean authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            // Compare the provided password with the stored encrypted password
            return passwordEncoder.matches(password, user.get().getPassword());
        }

        return false; // Username not found
    }

    @Override
    public Optional<User> findUser(String username) {
        Optional<User> user = Optional.empty();
        user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            // Compare the provided password with the stored encrypted password
            return user;
        }
        return user;
    }
}
