package com.testingTask.justAi.objects;


public class IncomingMessage {

    private Integer group_id;

    private String type;

    private String event_id;

    private String v;

    private MessageObject object;


    public Integer getGroup_id() {
        return group_id;
    }

    public String getType() {
        return type;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getV() {
        return v;
    }

    public MessageObject getObject() {
        return object;
    }
}
