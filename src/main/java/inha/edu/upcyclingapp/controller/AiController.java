package inha.edu.upcyclingapp.controller;


import inha.edu.upcyclingapp.dto.CommonResponse;
import inha.edu.upcyclingapp.dto.QuestionRequest;
import inha.edu.upcyclingapp.service.CertificationAiService;
import inha.edu.upcyclingapp.service.QuestionAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AiController {

    private final CertificationAiService certificationAiService;
    private final QuestionAiService questionAiService;

    @GetMapping("/ai")
    public String test() {
        return certificationAiService.getTestCertification();
    }

    @PostMapping("/ai/questions")
    public ResponseEntity<CommonResponse> question(@RequestBody QuestionRequest request) {
        String answer = questionAiService.getAnswer(request.getQuestion());
        return ResponseEntity.ok(new CommonResponse(answer));
    }
}
