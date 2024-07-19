package inha.edu.upcyclingapp.dto;

import inha.edu.upcyclingapp.model.Mission;
import lombok.Getter;

import java.util.List;

@Getter
public class MissionListResponse {
    private List<Mission> missions;

    public MissionListResponse(List<Mission> missions) {
        this.missions = missions;
    }
}
