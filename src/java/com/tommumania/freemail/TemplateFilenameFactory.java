package com.tommumania.freemail;

public class TemplateFilenameFactory {
    public String getFilename(MessageType messageType, EmailFormat emailFormat) {
        return messageType.getTemplatePrefix() + "-" + emailFormat.toString() + ".ftl";
    }
}
