package inha.edu.upcyclingapp.model.ai.bedrock;

public enum BedrockModelType {
    ANTHROPIC3_5_SONNET("anthropic.claude-3-5-sonnet-20240620-v1:0");

    private final String modelId;

    BedrockModelType(String modelId) {
        this.modelId = modelId;
    }

    public String getModelId() {
        return modelId;
    }
}