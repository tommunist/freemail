package com.tommumania.freemail;

public enum EmailFormat {
    HTML("html"), PLAIN_TEXT("plain");
    private String string;

    EmailFormat(String string) {
        this.string = string;
    }

    public String toString() {
        return string;
    }
}
