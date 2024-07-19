package inha.edu.upcyclingapp.controller;

import inha.edu.upcyclingapp.dto.*;
import inha.edu.upcyclingapp.model.Saving;
import inha.edu.upcyclingapp.model.User;
import inha.edu.upcyclingapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    @Value("${cloud.aws.cloudfront.domain}")
    private String cloudFrontDomain;

    private final UserService userService;
    private final SavingService savingService;
    private final MissionService missionService;
    private final LambdaService lambdaService;
    private final CertificationService certificationService;

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
    public ResponseEntity<MissionListResponse> getMissions(@PathVariable Long userId) {
        List<MissionResponse> responses = new ArrayList<>();

        missionService.getMissionsByUserId(userId).forEach(
                mission ->
                        responses.add(new MissionResponse(mission, certificationService.getCertificationImage(mission.getId())))
        );

        return ResponseEntity.ok(new MissionListResponse(responses));
    }

    @PostMapping("/users/{userId}/letters")
    public ResponseEntity<CommonResponse> makeLetter(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        lambdaService.invokeLambda("upcycling-lambda", String.format("""
                {
                  "name": "%s",
                  "userId": "%s"
                }
                """, user.getNickname(), user.getId()));

        return ResponseEntity.ok(new CommonResponse("success"));
    }


    @GetMapping("/users/{userId}/letters")
    public ResponseEntity<LetterResponse> readLetter(@PathVariable Long userId) {
        return ResponseEntity.ok(new LetterResponse(cloudFrontDomain + "/" + userId + "/pdf/certification.pdf"));
    }
}
