package inha.edu.upcyclingapp.model.ai;

import java.util.List;
import java.util.StringJoiner;

public record VisionResponse(List<Generation> generations) {

    public String join() {
        StringJoiner joiner = new StringJoiner("\n");
        for (Generation generation : generations) {
            joiner.add(generation.getAssistantMessage().getContent());
        }
        return joiner.toString();
    }
}