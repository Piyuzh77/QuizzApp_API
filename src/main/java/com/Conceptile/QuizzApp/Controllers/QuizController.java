package com.Conceptile.QuizzApp.Controllers;

import com.Conceptile.QuizzApp.Models.Problem;
import com.Conceptile.QuizzApp.Models.SubmissionRequest;
import com.Conceptile.QuizzApp.Models.SubmissionResponse;
import com.Conceptile.QuizzApp.Service.QuizService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    /**
     * Endpoint to get an unanswered problem for the quiz.
     * @param session The current HTTP session to track answered questions.
     * @return ResponseEntity containing the problem or no content if all problems are answered.
     */
    @GetMapping("/problem")
    public ResponseEntity<Problem> getUnansweredProblem(HttpSession session) {
        Problem problem = quizService.getUnansweredProblem(session);
        if (problem == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        }
        return new ResponseEntity<>(problem, HttpStatus.OK); 
    }

    /**
     * Endpoint to submit a user's answer for a problem.
     * @param submissionRequest The user's submitted answer.
     * @param session The current HTTP session to track progress.
     * @return ResponseEntity containing the submission response or bad request if any issue.
     */
    @PostMapping("/submit")
    public ResponseEntity<SubmissionResponse> submitAnswer(@RequestBody SubmissionRequest submissionRequest, HttpSession session) {
        try {
            SubmissionResponse response = quizService.submitAnswer(session, submissionRequest);
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
    }

    /**
     * Health check endpoint for the application.
     * @return Simple text response to check if the server is running.
     */
    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Working!!!!", HttpStatus.OK); 
    }
}
