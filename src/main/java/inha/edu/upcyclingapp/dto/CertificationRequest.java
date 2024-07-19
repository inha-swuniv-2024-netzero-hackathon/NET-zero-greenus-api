package inha.edu.upcyclingapp.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CertificationRequest {
    private Long userId;
    private String category;
    private MultipartFile image;

    public CertificationRequest(Long userId, String category, MultipartFile image) {
        this.userId = userId;
        this.category = category;
        this.image = image;
    }
}
