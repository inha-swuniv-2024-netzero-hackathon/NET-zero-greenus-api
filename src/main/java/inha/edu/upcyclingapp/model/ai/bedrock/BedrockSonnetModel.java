package inha.edu.upcyclingapp.model.ai.bedrock;

import inha.edu.upcyclingapp.model.ai.FoundationModel;
import inha.edu.upcyclingapp.model.ai.Message;
import inha.edu.upcyclingapp.model.ai.VisionOptions;
import inha.edu.upcyclingapp.model.ai.VisionResponse;
import software.amazon.awssdk.services.bedrockruntime.model.ConverseRequest;
import software.amazon.awssdk.services.bedrockruntime.model.ConverseResponse;

import java.util.Arrays;
import java.util.List;

public class BedrockSonnetModel implements FoundationModel {
    private final BedrockApi bedrockApi;
    private final VisionOptions chatOptions;
    private final BedrockModelType modelType;

    public BedrockSonnetModel(BedrockApi bedrockApi) {
        this(bedrockApi, VisionOptions.DEFAULT, BedrockModelType.ANTHROPIC3_5_SONNET);
    }

    public BedrockSonnetModel(BedrockApi bedrockApi, VisionOptions chatOptions, BedrockModelType modelType) {
        this.bedrockApi = bedrockApi;
        this.chatOptions = chatOptions;
        this.modelType = modelType;
    }

    @Override
    public VisionResponse call(Message... messages) {
        List<Message> messageList = Arrays.asList(messages);
        ConverseRequest request = BedrockApi.toConverseRequest(messageList, chatOptions, modelType.getModelId());
        ConverseResponse response = bedrockApi.converse(request);

        return BedrockApi.toChatResponse(response);
    }
}


