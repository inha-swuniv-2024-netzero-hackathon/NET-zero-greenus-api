package inha.edu.upcyclingapp.repository;

import inha.edu.upcyclingapp.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {
}
