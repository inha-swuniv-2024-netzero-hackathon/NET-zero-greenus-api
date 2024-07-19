package inha.edu.upcyclingapp.dto;

import inha.edu.upcyclingapp.model.Mission;
import lombok.Getter;

@Getter
public class MissionResponse {
    private String title;
    private String description;
    private Boolean isCompleted;
    private String imageUrl;

    public MissionResponse(Mission mission, String imageUrl) {
        this.title = mission.getMissionContent();
        this.description = description;
        this.isCompleted = mission.getIsCompleted();
        this.imageUrl = imageUrl;
    }
}
