package inha.edu.upcyclingapp.controller;


import inha.edu.upcyclingapp.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AiController {

    private final AiService aiService;

    @GetMapping("/ai")
    public String test() {
        return aiService.test();
    }

}
