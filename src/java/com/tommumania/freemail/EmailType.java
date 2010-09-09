package com.tommumania.freemail;

public enum EmailType {
    HELLO_WORLD("hello_world");
    private String templatePrefix;

    private EmailType(String templatePrefix) {
        this.templatePrefix = templatePrefix;
    }


    public String getTemplatePrefix() {
        return templatePrefix;
    }
}
