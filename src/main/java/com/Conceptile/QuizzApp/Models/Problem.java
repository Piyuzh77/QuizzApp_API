package com.Conceptile.QuizzApp.Models;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column (nullable=false, unique = true)
    private String problemStatement;

    @Column(nullable = false)
    private String correctAnswer;

    @ElementCollection
    @CollectionTable(name = "problem_options", joinColumns = @JoinColumn(name = "problem_id"))
    @Column(name = "option")
    private List<String> options; 
    
}
