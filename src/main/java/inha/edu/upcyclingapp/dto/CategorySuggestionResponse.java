package inha.edu.upcyclingapp.dto;

import lombok.Getter;

@Getter
public class CategorySuggestionResponse {
    private String suggestion;

    public CategorySuggestionResponse(String suggestion) {
        this.suggestion = suggestion;
    }
}
