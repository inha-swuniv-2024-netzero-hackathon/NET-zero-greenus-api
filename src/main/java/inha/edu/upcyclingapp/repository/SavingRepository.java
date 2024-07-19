package inha.edu.upcyclingapp.repository;

import inha.edu.upcyclingapp.model.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Long> {
    Optional<Saving> findByUserId(Long userId);
}
