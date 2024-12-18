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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USER_DATA")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column (nullable=false, unique = true)
    private String email;
    private String password;
    private String name;

    @ElementCollection
    @CollectionTable(name = "user_scores", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "score")
    private List<Integer> scores;
    
    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }    

}
