package com.tommumania.freemail;

import freemarker.template.TemplateException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.util.Map;

public class BodyPartFactory {
    private EmailContentGenerator emailContentGenerator;

    public BodyPartFactory(EmailContentGenerator emailContentGenerator) {

        this.emailContentGenerator = emailContentGenerator;
    }

    public BodyPart createHtmlBodyPart(Email email) throws IOException, TemplateException, MessagingException {
        MimeBodyPart bodyPart = new MimeBodyPart();
        final String html = emailContentGenerator.process(email.getContentParameters(), email.getMessageType(), EmailFormat.HTML);

        bodyPart.setDataHandler(htmlDataHandler(html));
        return bodyPart;
    }

    public BodyPart createPlainTextBodyPart(Email email) throws IOException, TemplateException, MessagingException {
        MimeBodyPart bodyPart = new MimeBodyPart();
        final String text = emailContentGenerator.process(email.getContentParameters(), email.getMessageType(), EmailFormat.PLAIN_TEXT);
                   
        bodyPart.setDataHandler(plainTextDataHandler(text));
        return bodyPart;
    }

    private DataHandler htmlDataHandler(final String html) {
        return new DataHandler(new DataSource() {
            public InputStream getInputStream() throws IOException {
                return new StringBufferInputStream(html);
            }

            public OutputStream getOutputStream() throws IOException {
                throw new IOException("Read-only data");
            }

            public String getContentType() {
                return "text/html";
            }

            public String getName() {
                return "main";
            }
        });
    }

    private DataHandler plainTextDataHandler(final String text) {
        return new DataHandler(new DataSource() {
                    public InputStream getInputStream() throws IOException {
                        return new StringBufferInputStream(text);
                    }
                    public OutputStream getOutputStream() throws IOException {
                        throw new IOException("Read-only data");
                    }
                    public String getContentType() {
                        return "text/plain";
                    }
                    public String getName() {
                        return "main";
                    }
        });
    }
}
