package inha.edu.upcyclingapp.model.ai;

import lombok.Getter;

@Getter
public abstract class Message {
    private final MessageType role;
    private final String content;

    public Message(MessageType role, String content) {
        this.role = role;
        this.content = content;
    }

    public static class UserMessage extends Message {
        public UserMessage(String content) {
            super(MessageType.USER, content);
        }
    }

    public static class AssistantMessage extends Message {
        public AssistantMessage(String content) {
            super(MessageType.ASSISTANT, content);
        }
    }
}

