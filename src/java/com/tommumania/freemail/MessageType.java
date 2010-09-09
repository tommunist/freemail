package com.tommumania.freemail;

public enum MessageType {
    HELLO_WORLD("hello_world");
    private String templatePrefix;

    private MessageType(String templatePrefix) {
        this.templatePrefix = templatePrefix;
    }


    public String getTemplatePrefix() {
        return templatePrefix;
    }
}
