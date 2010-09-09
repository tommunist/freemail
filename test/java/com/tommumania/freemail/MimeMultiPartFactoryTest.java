package com.tommumania.freemail;

import freemarker.template.TemplateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MimeMultiPartFactoryTest {

    private MimeMultiPartFactory factory;

    @Mock
    private EmailContentGenerator emailContentGenerator;

    @Before
    public void setUp() throws Exception {
        factory = new MimeMultiPartFactory(emailContentGenerator);
    }

    @Test
    public void shouldCreateMimeMessageFromEmail() throws IOException, TemplateException, MessagingException {
        Map<String, Object> map = mock(Map.class);
        Email email = mock(Email.class);

        when(emailContentGenerator.process(map, EmailType.HELLO_WORLD)).thenReturn("emailContent");

        Multipart multipart = factory.generate(email);

        assertThat(multipart.getContentType(), startsWith("multipart/alternative"));
        ;

    }
}
