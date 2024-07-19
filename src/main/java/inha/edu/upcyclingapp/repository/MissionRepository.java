package inha.edu.upcyclingapp.repository;

import inha.edu.upcyclingapp.model.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long>{
    List<Mission> findByUserIdAndIsCompletedFalse(Long userId);
    List<Mission> findByUserId(Long userId);
    Optional<Mission> findByUserIdAndMissionContent(Long userId, String missionContent);
}
