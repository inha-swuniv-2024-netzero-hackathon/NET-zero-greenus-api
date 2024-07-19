package inha.edu.upcyclingapp.service;

import inha.edu.upcyclingapp.dto.CertificationRequest;
import inha.edu.upcyclingapp.model.Certification;
import inha.edu.upcyclingapp.repository.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CertificationService {

    private final S3ImageProcessor s3ImageProcessor;
    private final AiService aiService;
    private final CertificationRepository certificationPhotoRepository;

    public void addCertification(CertificationRequest request) throws IOException {
        String key = getKey(request);
        s3ImageProcessor.upload(key, request.getImage());
        certificationPhotoRepository.save(Certification.builder()
                        .userId(request.getUserId())
                        .imagePath(key)
                        .category(aiService.getCertification(request.getImage().getBytes()))
                        .build());
    }

    private static String getKey(CertificationRequest request) {
        return String.format("/%s/", request.getUserId()) + UUID.randomUUID();
    }
}
