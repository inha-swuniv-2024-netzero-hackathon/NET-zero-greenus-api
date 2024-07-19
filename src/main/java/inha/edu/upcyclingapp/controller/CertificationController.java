package inha.edu.upcyclingapp.controller;

import inha.edu.upcyclingapp.dto.CategorySuggestionResponse;
import inha.edu.upcyclingapp.dto.CertificationRequest;
import inha.edu.upcyclingapp.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;

    @PostMapping("/certification")
    public ResponseEntity<?> addCertification(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("userId") Long userId,
                                                   @RequestParam("category") String category) {
        try {
            CertificationRequest request = new CertificationRequest(userId, category, file);
            String result = certificationService.addCertification(request);

            if (result == null) {
                return ResponseEntity.ok("success");
            }

            return ResponseEntity.status(400)
                    .body(new CategorySuggestionResponse(result));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}