package com.tommumania.freemail;

import freemarker.template.TemplateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FreeMailerTest {

    private FreeMailer freeMailer;

    @Mock
    private MimeMultiPartFactory multiPartFactory;
    @Mock
    private JavaMailSenderImpl mailSender;

    @Before
    public void setUp() throws Exception {
        freeMailer = new FreeMailer(multiPartFactory, mailSender);
    }

    @Test
    public void shouldSendEmailToRecipient() throws MessagingException, IOException, TemplateException {

        Email email = mock(Email.class);

        MimeMessage message = mock(MimeMessage.class);
        Multipart multipart = mock(Multipart.class);
        Map<String, Object> map = mock(Map.class);

        when(email.getContentParameters()).thenReturn(map);
        when(email.getRecipient()).thenReturn("to@example.com");
        when(email.getSender()).thenReturn("from@example.com");
        when(email.getSubject()).thenReturn("subject");
        when(email.getMessageType()).thenReturn(EmailType.HELLO_WORLD);

        when(mailSender.createMimeMessage()).thenReturn(message);
        when(multiPartFactory.generate(email)).thenReturn(multipart);

        freeMailer.send(email);

        verify(message).setRecipient(Message.RecipientType.TO, new InternetAddress("to@example.com"));
        verify(message).setFrom(new InternetAddress("from@example.com"));
        verify(message).setSubject("subject");
        verify(message).setContent(multipart);
        verify(mailSender).send(message);

    }

}