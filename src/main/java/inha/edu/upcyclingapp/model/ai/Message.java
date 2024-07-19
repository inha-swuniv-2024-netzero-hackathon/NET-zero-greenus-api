package inha.edu.upcyclingapp.model.ai;

import lombok.Getter;

@Getter
public abstract class Message {
    private final MessageType role;
    private final String content;
    private final byte[] image;

    public Message(MessageType role, String content, byte[] image) {
        this.role = role;
        this.content = content;
        this.image = image;
    }

    public static class UserMessage extends Message {
        public UserMessage(String content, byte[] image) {
            super(MessageType.USER, content, image);
        }
    }

    public static class AssistantMessage extends Message {
        public AssistantMessage(String content) {
            super(MessageType.ASSISTANT, content, null);
        }
    }
}

