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
    private EmailContentProcessor processor;

    @Before
    public void setUp() {
        EmailTypeToTemplateFilenameMapper emailTypeToTemplateFilenameMapper = new EmailTypeToTemplateFilenameMapper();
        TemplateFactory templatefactory = new TemplateFactory();
        processor = new EmailContentProcessor(templatefactory, emailTypeToTemplateFilenameMapper);

    }

    @Test
    public void shouldProcessTemplatesIntoEmailContent() throws IOException, TemplateException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "hello there matey");

        String content = processor.process(map, EmailType.HELLO_WORLD);
        assertThat(content, is("Your message: hello there matey"));
    }


}