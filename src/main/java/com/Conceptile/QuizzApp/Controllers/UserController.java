package com.Conceptile.QuizzApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Conceptile.QuizzApp.Models.User;
import com.Conceptile.QuizzApp.Repostiory.UserRepo;
import com.Conceptile.QuizzApp.Service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller to manage user-related actions such as registration and retrieval of users.
 */
@RestController
@RequestMapping("/users")  
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user by encoding their password before saving.
     * 
     * @param user The user object to be registered.
     * @return ResponseEntity containing the saved user data and HTTP status.
     * @throws BadRequestException If the provided user data is invalid.
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (user == null || user.getPassword() == null || user.getPassword().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userService.saveNewUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);  
    }

    /**
     * Retrieves all registered users.
     * 
     * @return ResponseEntity containing a list of users and HTTP status.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepo.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  
        }
        return new ResponseEntity<>(users, HttpStatus.OK);  
    }
}
