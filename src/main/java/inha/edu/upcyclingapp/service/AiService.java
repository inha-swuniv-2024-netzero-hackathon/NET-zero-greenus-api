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

    public String getCertification(byte[] image) {
        String response = foundationModel
                .call(new Message.UserMessage(getPrompt(), image))
                .join();

        if (response.equalsIgnoreCase("ERROR")) {
            throw new RuntimeException("죄송합니다. 응답을 만들지 못했습니다.");
        }
        return response;
    }

    public String getTestCertification() {
        String response = foundationModel
                .call(new Message.UserMessage(getPrompt(), getTestImage()))
                .join();

        if (response.equalsIgnoreCase("ERROR")) {
            throw new RuntimeException("죄송합니다. 응답을 만들지 못했습니다.");
        }
        return response;
    }

    private byte[] getTestImage() {
        Resource resource = resourceLoader.getResource("classpath:static/" + "1.jpg");

        try (InputStream inputStream = resource.getInputStream()) {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getPrompt() {
        return "이 사진은 녹색 실천에 대한 인증 사진이야. '다회용기 포장, 대중교통 이용, 걸음 수 인증' 셋 중 하나인데 뭐에 가장 가까운지를 알려줘. 단, 추가적인 설명은 필요 없고 정답만 간결하게 알려줘";
    }
}

