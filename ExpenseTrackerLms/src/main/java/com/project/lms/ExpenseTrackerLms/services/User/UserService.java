package com.project.lms.ExpenseTrackerLms.services.User;

import com.project.lms.ExpenseTrackerLms.dto.UserDTO;
import com.project.lms.ExpenseTrackerLms.entity.User;

import java.util.Optional;

public interface UserService {

    // Method for registering a user
    User registerUser(UserDTO userDTO);

    // Method for authenticating a user (login)
    boolean authenticateUser(String username, String password);

    Optional<User> findUser(String username);
}
