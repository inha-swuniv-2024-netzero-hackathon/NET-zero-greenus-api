package inha.edu.upcyclingapp.controller;

import inha.edu.upcyclingapp.dto.MissionResponse;
import inha.edu.upcyclingapp.dto.SavingDto;
import inha.edu.upcyclingapp.dto.UserResponse;
import inha.edu.upcyclingapp.model.Saving;
import inha.edu.upcyclingapp.model.User;
import inha.edu.upcyclingapp.service.MissionService;
import inha.edu.upcyclingapp.service.SavingService;
import inha.edu.upcyclingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final SavingService savingService;
    private final MissionService missionService;

    @GetMapping("/users/{userId}/savings")
    public ResponseEntity<UserResponse> getSavings(@PathVariable Long userId) {

        User user = userService.getUser(userId);
        Saving saving = savingService.getSaving(userId);

        return ResponseEntity.ok(new UserResponse(user, saving));
    }

    @PostMapping("/users/{userId}/savings")
    public ResponseEntity addSavings(@PathVariable Long userId, @RequestBody SavingDto request) {
        savingService.addSaving(userId, request);

        return ResponseEntity.created(null).build();
    }

    @GetMapping("/users/{userId}/missions")
    public ResponseEntity<MissionResponse> getMissions(@PathVariable Long userId) {
        return ResponseEntity.ok(new MissionResponse(missionService.getMissions(userId)));
    }
}
