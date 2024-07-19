package inha.edu.upcyclingapp.dto;

import lombok.Getter;

@Getter
public class QuestionRequest {
    private String question;

    public QuestionRequest(String question) {
        this.question = question;
    }
}
