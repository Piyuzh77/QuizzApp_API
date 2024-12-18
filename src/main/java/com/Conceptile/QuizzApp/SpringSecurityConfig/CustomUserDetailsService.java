package com.Conceptile.QuizzApp.SpringSecurityConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Conceptile.QuizzApp.Models.User;
import com.Conceptile.QuizzApp.Repostiory.UserRepo;


public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        try {
            User user = userRepo.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with email: " + email);
            }

            return org.springframework.security.core.userdetails.User.builder()
                    .username(email)
                    .password(user.getPassword())
                    .build();

        } catch (Exception e) {
            logger.error("Error occurred while loading user by email: {}", email, e);
            throw new UsernameNotFoundException("An error occurred while retrieving user details for email: " + email, e);
        }
    }
}
