package com.tommumania.freemail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FreeMailerTest {

    private FreeMailer freeMailer;

    @Mock
    private MimeMessageFactory messageFactory;
    @Mock
    private JavaMailSenderImpl mailSender;

    @Before
    public void setUp() throws Exception {
        freeMailer = new FreeMailer(messageFactory, mailSender);
    }

    @Test
    public void shouldSendEmailToRecipient() {

        Email email = mock(Email.class);
        MimeMessage message = mock(MimeMessage.class);

        when(messageFactory.generate(email)).thenReturn(message);

        freeMailer.send(email);

        verify(mailSender).send(message);

    }

}