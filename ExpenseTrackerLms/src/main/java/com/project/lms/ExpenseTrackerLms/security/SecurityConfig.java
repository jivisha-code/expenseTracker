//package com.project.lms.ExpenseTrackerLms.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    // This is the new way to configure security in Spring Security 6 and Spring Boot 3.x
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/register", "/login").permitAll()  // Allow access to register and login pages
//                .anyRequest().authenticated()  // All other requests require authentication
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()  // Allow all users to access the login page
//                .and()
//                .logout()
//                .permitAll();  // Allow all users to log out
//        return http.build();
//    }
//
//    // Password encoder bean to hash passwords using BCrypt
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
