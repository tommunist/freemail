package com.tommumania.freemail;


import freemarker.template.TemplateException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public class FreeMailer {
    private MimeMultiPartFactory multiPartFactory;
    private JavaMailSenderImpl mailSender;

    public FreeMailer(MimeMultiPartFactory multiPartFactory, JavaMailSenderImpl mailSender) {
        this.multiPartFactory = multiPartFactory;
        this.mailSender = mailSender;
    }


    public void send(Email email) throws MessagingException, IOException, TemplateException {
        Multipart multipart = multiPartFactory.generate(email);

        MimeMessage message = mailSender.createMimeMessage();
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));
        message.setFrom(new InternetAddress(email.getSender()));
        message.setSubject(email.getSubject());
        message.setContent(multipart);

        mailSender.send(message);
    }
}
