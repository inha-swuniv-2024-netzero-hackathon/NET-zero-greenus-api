package inha.edu.upcyclingapp.service;

import inha.edu.upcyclingapp.model.User;
import inha.edu.upcyclingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
