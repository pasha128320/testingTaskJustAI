package com.testingTask.justAi.objects;


import java.util.List;

public class Message {

    private Integer date;

    private Integer from_id;

    private Integer id;

    private Integer version;

    private List<Attachments> attachments;

    private Integer conversation_message_id;

    private Integer peer_id;

    private Integer random_id;

    private String text;

    public Integer getDate() {
        return date;
    }

    public List<Attachments> getAttachments() {
        return attachments;
    }

    public Integer getFrom_id() {
        return from_id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public Integer getConversation_message_id() {
        return conversation_message_id;
    }

    public Integer getPeer_id() {
        return peer_id;
    }

    public Integer getRandom_id() {
        return random_id;
    }

    public String getText() {
        return text;
    }
}
