package com.tommumania.freemail;

public enum EmailType {
    HELLO_WORLD("hello_world.ftl");
    private String filename;

    private EmailType(String filename) {
        this.filename = filename;
    }


    public String getFilename() {
        return filename;
    }
}
