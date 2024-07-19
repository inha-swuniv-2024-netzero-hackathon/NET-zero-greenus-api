package inha.edu.upcyclingapp.dto;

import inha.edu.upcyclingapp.model.Saving;
import inha.edu.upcyclingapp.model.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private Long id;
    private String name;
    private SavingDto saving;

    public UserResponse(User user, Saving saving) {
        this.id = user.getId();
        this.name = user.getNickname();
        this.saving = new SavingDto(saving);
    }
}
