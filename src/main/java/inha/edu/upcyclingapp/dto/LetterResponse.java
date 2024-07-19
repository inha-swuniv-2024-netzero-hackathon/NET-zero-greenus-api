package inha.edu.upcyclingapp.dto;

import lombok.Getter;

@Getter
public class LetterResponse {
    private String pdfLink;

    public LetterResponse(String pdfLink) {
        this.pdfLink = pdfLink;
    }
}
