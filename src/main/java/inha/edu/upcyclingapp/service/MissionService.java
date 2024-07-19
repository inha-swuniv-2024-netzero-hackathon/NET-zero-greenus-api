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

    public List<Mission> getMissions(Long userId) {
        return missionRepository.findByUserId(userId);
    }
}
