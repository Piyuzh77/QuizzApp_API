package com.Conceptile.QuizzApp.Models;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SubmissionRequest {
    private List<Answer> answers;

    @Data
    @Getter
    @Setter
    public static class Answer {
        private Integer problemId;
        private String selectedAnswer;
    }
}
