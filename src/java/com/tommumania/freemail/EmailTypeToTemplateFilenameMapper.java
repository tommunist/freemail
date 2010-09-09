package com.tommumania.freemail;

public class EmailTypeToTemplateFilenameMapper {
    public String map(EmailType emailType, EmailFormat emailFormat) {
        return emailType.getTemplatePrefix() + "-" + emailFormat.toString() + ".ftl";
    }
}
