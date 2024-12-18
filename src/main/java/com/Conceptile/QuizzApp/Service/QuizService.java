package com.Conceptile.QuizzApp.Service;

import com.Conceptile.QuizzApp.Models.Problem;
import com.Conceptile.QuizzApp.Models.SubmissionRequest;
import com.Conceptile.QuizzApp.Models.SubmissionRequest.Answer;
import com.Conceptile.QuizzApp.Models.SubmissionResponse;
import com.Conceptile.QuizzApp.Repostiory.ProblemRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private ProblemRepo problemRepo;

    /**
     * Retrieves an unanswered problem from the current user's session.
     * It checks if any problems have been answered. If so, it filters out those from the available problems.
     * If no unanswered problems remain, it returns all problems.
     *
     * @param session The current HTTP session.
     * @return A random unanswered Problem from the available set.
     */
    public Problem getUnansweredProblem(HttpSession session) {
        List<Integer> answeredQuestions = (List<Integer>) session.getAttribute("answeredQuestions");

        if (answeredQuestions == null) {
            answeredQuestions = new ArrayList<>();
            session.setAttribute("answeredQuestions", answeredQuestions);
        }

        List<Problem> allProblems = problemRepo.findAll();

        List<Problem> unansweredProblems = new ArrayList<>();
        for (Problem problem : allProblems) {
            if (!answeredQuestions.contains(problem.getId())) {
                unansweredProblems.add(problem);
            }
        }

        if (unansweredProblems.isEmpty()) {
            unansweredProblems = allProblems;
        }

        return unansweredProblems.get((int) (Math.random() * unansweredProblems.size()));
    }

    /**
     * Marks a problem as answered in the current user's session and stores their answer.
     * It also saves the user's answer to a separate list for potential review.
     *
     * @param session     The current HTTP session.
     * @param problem     The problem that was answered.
     * @param userAnswer  The answer provided by the user.
     */
    public void submitAnswer(HttpSession session, Problem problem, String userAnswer) {
        List<Integer> answeredQuestions = (List<Integer>) session.getAttribute("answeredQuestions");
        if (answeredQuestions == null) {
            answeredQuestions = new ArrayList<>();
        }

        List<String> userAnswers = (List<String>) session.getAttribute("userAnswers");
        if (userAnswers == null) {
            userAnswers = new ArrayList<>();
            session.setAttribute("userAnswers", userAnswers);
        }
        userAnswers.add(userAnswer);

        answeredQuestions.add(problem.getId());
        session.setAttribute("answeredQuestions", answeredQuestions);
    }

    /**
     * Evaluates the user's submission based on the provided answers and updates the session.
     * It also calculates the score based on correct answers.
     *
     * @param session             The current HTTP session.
     * @param submissionRequest   The submission request containing the problem and answers.
     * @return A SubmissionResponse containing the total questions, correct answers, and score.
     */
    public SubmissionResponse submitAnswer(HttpSession session, SubmissionRequest submissionRequest) {
        List<Integer> answeredQuestions = (List<Integer>) session.getAttribute("answeredQuestions");
        if (answeredQuestions == null) {
            answeredQuestions = new ArrayList<>();
        }

        int correctAnswers = 0;
        int totalQuestions = submissionRequest.getAnswers().size();

        for (Answer answer : submissionRequest.getAnswers()) {
            Problem problem = problemRepo.findById(answer.getProblemId())
                    .orElseThrow(() -> new RuntimeException("Problem not found"));

            boolean isCorrect = problem.getCorrectAnswer().equals(answer.getSelectedAnswer());

            if (isCorrect) {
                answeredQuestions.add(problem.getId());
                correctAnswers++;
            }
        }

        session.setAttribute("answeredQuestions", answeredQuestions);

        SubmissionResponse response = new SubmissionResponse();
        response.setTotalQuestions(totalQuestions);
        response.setCorrectAnswers(correctAnswers);
        response.setScore((correctAnswers * 100) / totalQuestions); 

        return response;
    }
}
