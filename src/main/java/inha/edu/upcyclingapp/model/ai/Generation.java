package inha.edu.upcyclingapp.model.ai;

public class Generation {
    private final Message.AssistantMessage assistantMessage;

    public Generation(Message.AssistantMessage assistantMessage) {
        this.assistantMessage = assistantMessage;
    }

    public Message.AssistantMessage getAssistantMessage() {
        return assistantMessage;
    }
}