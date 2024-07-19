package inha.edu.upcyclingapp.service;

import inha.edu.upcyclingapp.model.Mission;
import inha.edu.upcyclingapp.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MissionService {

    private final MissionRepository missionRepository;

    public List<Mission> getMissionsByUserId(Long userId) {
        return missionRepository.findByUserId(userId);
    }

    public Mission getMissionsById(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow();
    }
}
