package inha.edu.upcyclingapp.controller;

import inha.edu.upcyclingapp.dto.CertificationRequest;
import inha.edu.upcyclingapp.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final CertificationService certificationService;

    @PostMapping("/certification")
    public ResponseEntity<String> addCertification(@RequestBody CertificationRequest request) {
        try {
            certificationService.addCertification(request);
            return ResponseEntity.ok("success");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}