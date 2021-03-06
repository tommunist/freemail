package com.tommumania.freemail;

import freemarker.template.TemplateException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EmailContentProcessorIntegrationTest {
    private EmailContentGenerator generator;

    @Before
    public void setUp() {
        TemplateFilenameFactory templateFilenameFactory = new TemplateFilenameFactory();
        TemplateFactory templatefactory = new TemplateFactory();
        generator = new EmailContentGenerator(templatefactory, templateFilenameFactory);

    }

    @Test
    public void shouldProcessTemplatesIntoEmailContent() throws IOException, TemplateException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "hello there matey");

        String content = generator.process(map, MessageType.HELLO_WORLD, EmailFormat.PLAIN_TEXT);
        assertThat(content, is("Your message: hello there matey"));
    }


}