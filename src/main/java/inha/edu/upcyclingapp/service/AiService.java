package inha.edu.upcyclingapp.service;

import inha.edu.upcyclingapp.model.ai.FoundationModel;
import inha.edu.upcyclingapp.model.ai.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class AiService {

    private final ResourceLoader resourceLoader;
    private final FoundationModel foundationModel;

    public String generateKeywordRecommendationPrompt() {
        Resource resource = resourceLoader.getResource("classpath:static/" + "example.jpg");

        try (InputStream inputStream = resource.getInputStream()) {
            return Arrays.toString(inputStream.readAllBytes());
        } catch (IOException e) {
            System.out.println("error");
        }

        return "";
    }

    public String test() {
        String prompt = generateKeywordRecommendationPrompt();

        System.out.println("prompt: " + prompt);

        String response = foundationModel.call(new Message.UserMessage(prompt)).join();

        System.out.println(response);

        if (response.equalsIgnoreCase("ERROR")) {
            throw new RuntimeException("죄송합니다. 추천할만한 확장 검색 구문을 만들지 못했습니다.");
        }
        return response;
    }
}