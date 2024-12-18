package com.Conceptile.QuizzApp.Repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Conceptile.QuizzApp.Models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {


    User findByEmail(String email);
    
}
