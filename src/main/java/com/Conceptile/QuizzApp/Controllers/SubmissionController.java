package com.Conceptile.QuizzApp.Controllers;

import com.Conceptile.QuizzApp.Models.SubmissionRequest;
import com.Conceptile.QuizzApp.Models.SubmissionResponse;
import com.Conceptile.QuizzApp.Service.QuizService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    @Autowired
    private QuizService quizService;  

    /**
     * Endpoint to submit answers for the quiz problems.
     * @param submissionRequest Contains a list of answers for the problems.
     * @return ResponseEntity containing the submission response or a bad request status if input is invalid.
     */
    @PostMapping
    public ResponseEntity<SubmissionResponse> submitAnswers(@RequestBody SubmissionRequest submissionRequest, HttpSession session) {
        if (submissionRequest == null || submissionRequest.getAnswers() == null || submissionRequest.getAnswers().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  
        }

        try {
            SubmissionResponse response = quizService.submitAnswer(session, submissionRequest); 
            return new ResponseEntity<>(response, HttpStatus.OK);  
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);  
        }
    }
}
