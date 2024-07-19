package inha.edu.upcyclingapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class TestController {

    @GetMapping("/health")
    public String test() {
        return "health";
    }
}
