package com.tommumania.freemail;

import java.util.Map;

public class Email {
    private String recipient;
    private MessageType messageType;
    private String sender;
    private Map<String, Object> contentParameters;
    private String subject;

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setContentParameters(Map<String, Object> contentParameters) {
        this.contentParameters = contentParameters;
    }

    public String getRecipient() {
        return recipient;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getSender() {
        return sender;
    }

    public Map<String, Object> getContentParameters() {
        return contentParameters;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
