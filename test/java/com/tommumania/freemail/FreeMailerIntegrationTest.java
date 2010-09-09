package com.tommumania.freemail;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import freemarker.template.TemplateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.icegreen.greenmail.util.GreenMailUtil.getBody;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FreeMailerIntegrationTest {
    private GreenMail greenMail;
    private FreeMailer freeMailer;

    @Before
    public void setUp() throws Exception {
        greenMail = new GreenMail(ServerSetupTest.ALL);
        greenMail.start();

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("localhost");
        mailSender.setPort(3025);
        EmailContentGenerator generator = new EmailContentGenerator(new TemplateFactory(), new EmailTypeToTemplateFilenameMapper());
        freeMailer = new FreeMailer(new MimeMultiPartFactory(new BodyPartFactory(generator)), mailSender);
    }

    @After
    public void tearDown() {
        greenMail.stop();
    }

    @Test
    public void shouldSendHelloWorldEmailToRecipient() throws MessagingException, IOException, TemplateException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message", "harro, how you go??");
        
        Email email = new Email();
        email.setRecipient("test@example.com");
        email.setMessageType(EmailType.HELLO_WORLD);
        email.setSender("tom@example.com");
        email.setContentParameters(map);
        email.setSubject("test subject");

        freeMailer.send(email);

        assertThat("body", is(getBody(mostRecentMessage())));

    }

    private MimeMessage mostRecentMessage() {
        return greenMail.getReceivedMessages()[0];
    }
}
