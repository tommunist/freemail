package com.tommumania.freemail;

import freemarker.template.TemplateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BodyPartFactoryTest {

    @Mock
    private EmailContentGenerator emailContentGenerator;
    private BodyPartFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new BodyPartFactory(emailContentGenerator);
    }

    @Test
    public void shouldGenerateHtmlContent() throws MessagingException, IOException, TemplateException {
        Map<String, Object> context = mock(Map.class);
        Email email = mock(Email.class);
        when(email.getContentParameters()).thenReturn(context);
        when(email.getMessageType()).thenReturn(MessageType.HELLO_WORLD);

        when(emailContentGenerator.process(context, MessageType.HELLO_WORLD, EmailFormat.HTML)).thenReturn("<html>content</html>");
        BodyPart bodyPart = factory.createHtmlBodyPart(email);

        assertThat((String)bodyPart.getContent(), is("<html>content</html>"));
        assertThat(bodyPart.getContentType(), is("text/html"));

    }
    
    @Test
    public void shouldGeneratePlainTextContent() throws MessagingException, IOException, TemplateException {
        Map<String, Object> context = mock(Map.class);
        Email email = mock(Email.class);
        when(email.getContentParameters()).thenReturn(context);
        when(email.getMessageType()).thenReturn(MessageType.HELLO_WORLD);

        when(emailContentGenerator.process(context, MessageType.HELLO_WORLD, EmailFormat.PLAIN_TEXT)).thenReturn("content");
        BodyPart bodyPart = factory.createPlainTextBodyPart(email);

        assertThat((String)bodyPart.getContent(), is("content"));
        assertThat(bodyPart.getContentType(), is("text/plain"));

    }
}
