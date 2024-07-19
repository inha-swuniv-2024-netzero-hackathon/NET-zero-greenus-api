package inha.edu.upcyclingapp.model.ai;

public interface FoundationModel {
    VisionResponse call(Message... messages);
}
