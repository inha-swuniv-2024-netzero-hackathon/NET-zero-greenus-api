package inha.edu.upcyclingapp.dto;

import inha.edu.upcyclingapp.model.Mission;
import lombok.Getter;

import java.util.List;

@Getter
public class MissionResponse {
    private List<Mission> missions;

    public MissionResponse(List<Mission> missions) {
        this.missions = missions;
    }
}
