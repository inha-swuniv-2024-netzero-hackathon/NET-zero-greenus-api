package inha.edu.upcyclingapp.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MissionListResponse {
    private List<MissionResponse> missions;

    public MissionListResponse(List<MissionResponse> missions) {
        this.missions = missions;
    }
}
