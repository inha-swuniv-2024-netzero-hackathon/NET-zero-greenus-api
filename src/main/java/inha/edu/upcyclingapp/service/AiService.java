package inha.edu.upcyclingapp.service;

import inha.edu.upcyclingapp.model.ai.FoundationModel;
import inha.edu.upcyclingapp.model.ai.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Service
public class AiService {

    private final ResourceLoader resourceLoader;
    private final FoundationModel foundationModel;

    public String getCategory() {
        String response = foundationModel
                .call(new Message.UserMessage(getPrompt(), getImage()))
                .join();

        if (response.equalsIgnoreCase("ERROR")) {
            throw new RuntimeException("죄송합니다. 추천할만한 확장 검색 구문을 만들지 못했습니다.");
        }
        return response;
    }

    private byte[] getImage() {
        Resource resource = resourceLoader.getResource("classpath:static/" + "example.jpg");

        try (InputStream inputStream = resource.getInputStream()) {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            System.out.println("error");
        }

        return null;
    }

    private String getPrompt() {
        return "유리,플라스틱,캔,도자기,고철,전자제품,전구,타이어,의류,신발,가구,직물,가방,매트리스,카펫,커튼,세라믹,형광등,스티로폼,안경,비닐,종이,목재 중에 어디 카테고리에 속하는 제품일지 선택해줘. 다른 설명은 빼고 정확히 카테고리만 알려줘";
    }
}

