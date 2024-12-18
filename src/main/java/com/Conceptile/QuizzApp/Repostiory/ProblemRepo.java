package com.Conceptile.QuizzApp.Repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Conceptile.QuizzApp.Models.Problem;

@Repository
public interface ProblemRepo extends JpaRepository<Problem,Integer> {
    
}
