package inha.edu.upcyclingapp.dto;

import lombok.Getter;

@Getter
public class CommonResponse {
    private String message;

    public CommonResponse(String message) {
        this.message = message;
    }
}
