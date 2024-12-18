package com.Conceptile.QuizzApp.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SubmissionResponse {
    private int totalQuestions;
    private int correctAnswers;
    private int score;
}
    