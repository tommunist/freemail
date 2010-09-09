package com.tommumania.freemail;

public class EmailTypeToTemplateFilenameMapper {
    public String map(EmailType emailType) {
        return emailType.getFilename();
    }
}
