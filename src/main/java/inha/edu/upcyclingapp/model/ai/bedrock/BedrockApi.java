package inha.edu.upcyclingapp.model.ai.bedrock;

import inha.edu.upcyclingapp.model.ai.Message;
import inha.edu.upcyclingapp.model.ai.*;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.*;

import java.util.List;
import java.util.stream.Collectors;


public class BedrockApi {
    private final BedrockRuntimeClient bedrockRuntimeClient;

    public BedrockApi(BedrockRuntimeClient bedrockRuntimeClient) {
        this.bedrockRuntimeClient = bedrockRuntimeClient;
    }

    public ConverseResponse converse(ConverseRequest request) {
        return bedrockRuntimeClient.converse(request);
    }

    public static ConverseRequest toConverseRequest(
            List<Message> messages,
            VisionOptions chatOptions,
            String modelId
    ) {
        ConverseRequest.Builder builder = ConverseRequest.builder();

        builder.inferenceConfig(configBuilder -> {
            configBuilder.temperature(chatOptions.temperature());
            configBuilder.topP(chatOptions.topP());
        });

        builder.modelId(modelId);

        List<software.amazon.awssdk.services.bedrockruntime.model.Message> sdkMessages = messages.stream()
                .map(message -> software.amazon.awssdk.services.bedrockruntime.model.Message.builder()
                        .role(message.getRole() == MessageType.USER ? ConversationRole.USER : ConversationRole.ASSISTANT)
                        .content(List.of(
                                ContentBlock.fromImage(ImageBlock.builder()
                                                .source(ImageSource.builder()
                                                        .bytes(SdkBytes.fromUtf8String(message.getContent()))
                                                        .build())
                                                .format("jpeg")
                                        .build()),
                                ContentBlock.fromText("어디 카테고리에 속하는 제품일까요?"))
                        )
                        .build())
                .collect(Collectors.toList());

        builder.messages(sdkMessages);

        return builder.build();
    }

    public static VisionResponse toChatResponse(ConverseResponse response) {
        List<Generation> generations = response.output().message().content().stream()
                .filter(contentBlock -> contentBlock.type() == ContentBlock.Type.TEXT)
                .map(contentBlock -> new Generation(
                        new Message.AssistantMessage(contentBlock.text())
                ))
                .collect(Collectors.toList());

        return new VisionResponse(generations);
    }
}