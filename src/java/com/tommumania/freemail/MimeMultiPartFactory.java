package com.tommumania.freemail;

import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MimeMultiPartFactory {
    private EmailContentGenerator emailContentGenerator;

    public MimeMultiPartFactory(EmailContentGenerator emailContentGenerator) {
        this.emailContentGenerator = emailContentGenerator;
    }

    public Multipart generate(Email email) {
        MimeMultipart multipart = new MimeMultipart("alternative");

        

        return multipart;
    }



}
