package com.Conceptile.QuizzApp.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Conceptile.QuizzApp.Models.User;
import com.Conceptile.QuizzApp.Repostiory.UserRepo;

import jakarta.transaction.Transactional;

@Service  
public class UserService {  

    @Autowired  
    private UserRepo userRepo;  

    @Autowired  
    private PasswordEncoder passwordEncoder;  

    /**
     * Saves a new user to the database. It validates the input user and checks if the user already exists.
     * If the password is provided, it will be encoded before saving.
     * 
     * @param user The user object to be saved.
     * @return The saved user object.
     * @throws IllegalArgumentException if the user is null or the user already exists in the database.
     */
    @Transactional  
    public User saveNewUser(User user) {  
        if (user == null) {  
            throw new IllegalArgumentException("User cannot be null");  
        }  

        if (user.getID() != null) {  
            Optional<User> existingUserById = userRepo.findById(user.getID());  
            if (existingUserById.isPresent()) {  
                throw new IllegalArgumentException("User with ID " + user.getID() + " already exists");  
            }  
        }

        if (user.getPassword() != null) {  
            user.setPassword(passwordEncoder.encode(user.getPassword()));  
        }  

        return userRepo.save(user);  
    }  

    /**
     * Retrieves a user from the database by their ID.
     * 
     * @param id The ID of the user to be retrieved.
     * @return The user object.
     * @throws NoSuchElementException if the user with the given ID is not found.
     */
    public User getUserById(Integer id) {  
        Optional<User> usr = userRepo.findById(id);  
        if (usr.isPresent()) {  
            return usr.get();  
        } else {  
            throw new NoSuchElementException("User with ID " + id + " not found");  
        }  
    }  

    /**
     * Retrieves the score history of a user.
     * 
     * @param user The user object whose score history is to be retrieved.
     * @return The list of scores for the user.
     * @throws NoSuchElementException if the user with the given ID is not found.
     */
    public List<Integer> getUserScoreHistory(User user) {  
        Optional<User> usr = userRepo.findById(user.getID());  
        if (usr.isPresent()) {  
            return usr.get().getScores();  
        } else {  
            throw new NoSuchElementException("User with ID " + user.getID() + " not found");  
        }  
    }  

    /**
     * Updates the name of a user.
     * 
     * @param user The user object with the updated name.
     * @return The updated user object.
     * @throws IllegalArgumentException if the user or user ID is null.
     * @throws NoSuchElementException if the user with the given ID is not found.
     */
    public User updateUsers_Name(User user) {  
        if (user == null || user.getID() == null) {  
            throw new IllegalArgumentException("User or user ID cannot be null");  
        }  

        Optional<User> usr = userRepo.findById(user.getID());  
        if (usr.isPresent()) {  
            User existingUser = usr.get();  
            existingUser.setName(user.getName());  
            return userRepo.save(existingUser);  
        } else {  
            throw new NoSuchElementException("User with ID " + user.getID() + " not found");  
        }  
    }  
}
