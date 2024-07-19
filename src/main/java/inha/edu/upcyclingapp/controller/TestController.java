package inha.edu.upcyclingapp.controller;

import inha.edu.upcyclingapp.model.User;
import inha.edu.upcyclingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final UserRepository userRepository;

    @GetMapping("/health")
    public String test() {
        return "healthy";
    }

    @GetMapping("/users")
    public List<User> users() {
        return userRepository.findAll();
    }
}
