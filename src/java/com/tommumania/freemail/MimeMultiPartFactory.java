package com.tommumania.freemail;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;

public class MimeMultiPartFactory {
    private BodyPartFactory bodyPartFactory;

    public MimeMultiPartFactory(BodyPartFactory bodyPartFactory) {
        this.bodyPartFactory = bodyPartFactory;
    }

    public Multipart generate(Email email) throws MessagingException, IOException, TemplateException {
        MimeMultipart multipart = new MimeMultipart("alternative");

        multipart.addBodyPart(bodyPartFactory.createPlainTextBodyPart(email.getContentParameters(), email.getMessageType()));
        multipart.addBodyPart(bodyPartFactory.createHtmlBodyPart(email.getContentParameters(), email.getMessageType()));
        return multipart;
    }


}
