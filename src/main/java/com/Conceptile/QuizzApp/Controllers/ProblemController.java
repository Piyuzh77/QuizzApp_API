package com.Conceptile.QuizzApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Conceptile.QuizzApp.Models.Problem;
import com.Conceptile.QuizzApp.Service.ProblemService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    /**
     * Add multiple problems to the database
     * @param problems List of problems to add
     * @return ResponseEntity with a status and created problems
     */
    @PostMapping("/add")
    public ResponseEntity<List<Problem>> addProblems(@RequestBody List<Problem> problems) {
        if (problems == null || problems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            List<Problem> createdProblems = problemService.addAllProblem(problems);
            return new ResponseEntity<>(createdProblems, HttpStatus.CREATED); 
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    /**
     * Retrieve all problems
     * @return ResponseEntity with a list of problems
     */
    @GetMapping
    public ResponseEntity<List<Problem>> getProblems() {
        List<Problem> problems = problemService.getAllProblems();
        if (problems == null || problems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        }
        return new ResponseEntity<>(problems, HttpStatus.OK); 
    }
}
