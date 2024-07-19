package inha.edu.upcyclingapp.model.ai;

public record VisionOptions(float temperature, float topP, int topK) {
    public static final float DEFAULT_TEMPERATURE = 0.8f;
    public static final float DEFAULT_TOP_P = 0.9f;
    public static final int DEFAULT_TOP_K = 250;

    public static final VisionOptions DEFAULT = new VisionOptions(
            DEFAULT_TEMPERATURE,
            DEFAULT_TOP_P,
            DEFAULT_TOP_K
    );

}