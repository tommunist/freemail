package com.tommumania.freemail;

import freemarker.template.TemplateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MimeMultiPartFactoryTest {

    private MimeMultiPartFactory factory;

    @Mock
    private BodyPartFactory bodyPartFactory;

    @Before
    public void setUp() throws Exception {
        factory = new MimeMultiPartFactory(bodyPartFactory);
    }

    @Test
    public void shouldCreateMimeMessageFromEmail() throws IOException, TemplateException, MessagingException {
        Map<String, Object> map = mock(Map.class);
        BodyPart htmlBodyPart = mock(BodyPart.class);
        BodyPart plainTextBodyPart = mock(BodyPart.class);
        Email email = mock(Email.class);
        when(email.getContentParameters()).thenReturn(map);
        when(email.getMessageType()).thenReturn(MessageType.HELLO_WORLD);

        when(bodyPartFactory.createHtmlBodyPart(email)).thenReturn(htmlBodyPart);
        when(bodyPartFactory.createPlainTextBodyPart(email)).thenReturn(plainTextBodyPart);

        Multipart multipart = factory.generate(email);

        assertThat(multipart.getContentType(), startsWith("multipart/alternative"));
        assertThat(multipart.getBodyPart(0), is(plainTextBodyPart));
        assertThat(multipart.getBodyPart(1), is(htmlBodyPart));
    }
}
