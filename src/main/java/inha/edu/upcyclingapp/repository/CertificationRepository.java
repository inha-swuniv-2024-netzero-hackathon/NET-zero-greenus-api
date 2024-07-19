package inha.edu.upcyclingapp.repository;

import inha.edu.upcyclingapp.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {
    Optional<Certification> findByMissionId(Long missionId);
}
