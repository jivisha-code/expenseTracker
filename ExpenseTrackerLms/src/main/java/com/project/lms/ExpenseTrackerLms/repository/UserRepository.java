package com.project.lms.ExpenseTrackerLms.repository;

import com.project.lms.ExpenseTrackerLms.entity.User;  // Import the User entity
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query to find a user by their username
    Optional<User> findByUsername(String username);
}