package inha.edu.upcyclingapp.service;

import inha.edu.upcyclingapp.dto.CertificationRequest;
import inha.edu.upcyclingapp.model.Certification;
import inha.edu.upcyclingapp.model.Mission;
import inha.edu.upcyclingapp.repository.CertificationRepository;
import inha.edu.upcyclingapp.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CertificationService {

    @Value("${cloud.aws.cloudfront.domain}")
    private String cloudFrontDomain;

    private final S3ImageProcessor s3ImageProcessor;
    private final CertificationAiService aiService;
    private final CertificationRepository certificationRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public String addCertification(CertificationRequest request) throws IOException {

        String aiCategory = aiService.getCertification(request.getImage().getBytes());

        if (!aiCategory.equals(request.getCategory())) {
            return aiCategory;
        } else {
            String key = getKey(request);
            s3ImageProcessor.upload(key, request.getImage());

            Mission mission = missionRepository.findByUserIdAndMissionContent(request.getUserId(), request.getCategory()).orElseGet(Mission::new);

            certificationRepository.save(Certification.builder()
                            .userId(request.getUserId())
                            .missionId(mission.getId())
                            .imagePath(key)
                            .category(aiCategory).build());

            mission.completeMission();
            return null;
        }
    }

    public String getCertificationImage(Long missionId) {
        Certification certification = certificationRepository.findByMissionId(missionId).orElseThrow();
        return cloudFrontDomain + certification.getImagePath();
    }

    private static String getKey(CertificationRequest request) {
        return String.format("%s/", request.getUserId()) + UUID.randomUUID();
    }
}
