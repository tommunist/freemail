package com.tommumania.freemail;

import java.util.Map;

public class Email {
    private String recipient;
    private EmailType messageType;
    private String sender;
    private Map<String, Object> contentParamaters;

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setMessageType(EmailType messageType) {
        this.messageType = messageType;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setContentParamaters(Map<String, Object> contentParamaters) {
        this.contentParamaters = contentParamaters;
    }
}
