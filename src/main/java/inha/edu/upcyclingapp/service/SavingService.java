package inha.edu.upcyclingapp.service;

import inha.edu.upcyclingapp.dto.SavingDto;
import inha.edu.upcyclingapp.model.Saving;
import inha.edu.upcyclingapp.repository.SavingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SavingService {

    private final SavingRepository savingRepository;

    public Saving getSaving(Long userId) {
        return savingRepository.findByUserId(userId)
                .orElse(null);
    }

    public void addSaving(Long userId, SavingDto request) {
        savingRepository.save(request.toEntity(userId));
    }
}
