package com.tommumania.freemail;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;

public class FreeMailer {
    private MimeMessageFactory messageFactory;
    private JavaMailSenderImpl mailSender;

    public FreeMailer(MimeMessageFactory messageFactory, JavaMailSenderImpl mailSender) {
        this.messageFactory = messageFactory;
        this.mailSender = mailSender;
    }


    public void send(Email email) {
        MimeMessage message = messageFactory.generate(email);
        mailSender.send(message);
    }
}
