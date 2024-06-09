package com.testingTask.justAi.objects;



public class MessageObject {

    private Message message;

    private boolean keyboard;

    private boolean inline_keyboard;

    private boolean carousel;

    private Integer lang_id;

    public Message getMessage() {
        return message;
    }

    public boolean isKeyboard() {
        return keyboard;
    }

    public boolean isInline_keyboard() {
        return inline_keyboard;
    }

    public boolean isCarousel() {
        return carousel;
    }

    public Integer getLang_id() {
        return lang_id;
    }
}
