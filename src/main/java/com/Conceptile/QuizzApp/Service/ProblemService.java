package com.Conceptile.QuizzApp.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Conceptile.QuizzApp.Models.Problem;
import com.Conceptile.QuizzApp.Models.SubmissionRequest;
import com.Conceptile.QuizzApp.Models.SubmissionResponse;
import com.Conceptile.QuizzApp.Repostiory.ProblemRepo;

@Service
public class ProblemService {
    
    @Autowired
    private ProblemRepo problemRepo;

    /**
     * Fetches a random problem from the repository.
     * If no problems exist, it throws a NoSuchElementException.
     * 
     * @return A random Problem object if available.
     * @throws NoSuchElementException if no problems exist in the repository.
     */
    public Problem getARandomProblem() {
        List<Problem> allProblems = problemRepo.findAll();
        if (!allProblems.isEmpty()) {
            return allProblems.get((int) (Math.random() * allProblems.size()));
        }
        throw new NoSuchElementException("There are no problems to show.");
    }

    /**
     * Adds a single problem to the repository.
     * If the provided problem is null, a NoSuchElementException is thrown.
     * 
     * @param problem The Problem object to be added.
     * @return The saved Problem object.
     * @throws NoSuchElementException if the provided problem is null.
     */
    public Problem addProblem(Problem problem) {
        if (problem != null) {
            return problemRepo.save(problem); 
        }
        throw new NoSuchElementException("Please provide a valid problem to save.");
    }

    /**
     * Adds multiple problems to the repository.
     * If the provided list is null, a NoSuchElementException is thrown.
     * 
     * @param problems A list of Problem objects to be added.
     * @return A list of saved Problem objects.
     * @throws NoSuchElementException if the provided list is null.
     */
    public List<Problem> addAllProblem(List<Problem> problems) {
        if (problems != null) {
            return problemRepo.saveAll(problems); 
        }
        throw new NoSuchElementException("Please provide a list of problems to save.");
    }

    /**
     * Deletes a given problem from the repository.
     * If the provided problem is null, a NoSuchElementException is thrown.
     * 
     * @param problem The Problem object to be deleted.
     * @return A success message indicating deletion.
     * @throws NoSuchElementException if the provided problem is null.
     */
    public String deleteProblem(Problem problem) {
        if (problem != null) {
            problemRepo.delete(problem); 
            return "Deleted Problem"; 
        }
        throw new NoSuchElementException("Invalid problem to delete.");
    }

    /**
     * Updates an existing problem in the repository.
     * If the problem ID does not exist or fields are empty, a NoSuchElementException is thrown.
     * 
     * @param problem The Problem object containing updated details.
     * @return The updated Problem object.
     * @throws NoSuchElementException if the problem ID does not exist or fields are empty.
     */
    public Problem updateProblem(Problem problem) {
        Optional<Problem> existingProblem = problemRepo.findById(problem.getId());
        if (existingProblem.isPresent()) {
            Problem updatedProblem = existingProblem.get();
            if (problem != null) {
                updatedProblem.setProblemStatement(problem.getProblemStatement());
                updatedProblem.setCorrectAnswer(problem.getCorrectAnswer());
                updatedProblem.setOptions(problem.getOptions());
                return problemRepo.save(updatedProblem);
            }
        }
        throw new NoSuchElementException("Empty field or problem not found.");
    }

    /**
     * Evaluates a quiz submission, comparing answers to correct answers.
     * 
     * @param submissionRequest The SubmissionRequest containing the answers.
     * @return A SubmissionResponse containing total questions, correct answers, and score.
     */
    public SubmissionResponse evaluateSubmission(SubmissionRequest submissionRequest) {
        List<SubmissionRequest.Answer> answers = submissionRequest.getAnswers();
        int correctCount = 0;

        for (SubmissionRequest.Answer answer : answers) {
            Optional<Problem> problem = problemRepo.findById(answer.getProblemId());
            if (problem.isPresent() && problem.get().getCorrectAnswer().equals(answer.getSelectedAnswer())) {
                correctCount++;
            }
        }

        int totalQuestions = answers.size();
        int score = (int) ((double) correctCount / totalQuestions * 100); 
        
        SubmissionResponse response = new SubmissionResponse();
        response.setTotalQuestions(totalQuestions);
        response.setCorrectAnswers(correctCount);
        response.setScore(score);

        return response;
    }

    /**
     * Retrieves all problems from the repository.
     * 
     * @return A list of all Problem objects.
     */
    public List<Problem> getAllProblems() {
        return problemRepo.findAll(); 
    }
}
