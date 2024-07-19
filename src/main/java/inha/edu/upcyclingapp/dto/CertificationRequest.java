package inha.edu.upcyclingapp.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CertificationRequest {
    private Long userId;
    private MultipartFile image;

    public CertificationRequest(Long userId, MultipartFile image) {
        this.userId = userId;
        this.image = image;
    }
}
